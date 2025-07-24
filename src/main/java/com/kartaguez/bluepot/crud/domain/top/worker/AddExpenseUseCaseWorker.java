package com.kartaguez.bluepot.crud.domain.top.worker;

import com.kartaguez.bluepot.crud.domain.bottom.repository.ExpenseRepository;
import com.kartaguez.bluepot.crud.domain.bottom.repository.PotGlobalVersionRepository;
import com.kartaguez.bluepot.crud.domain.bottom.repository.PotRepository;
import com.kartaguez.bluepot.crud.domain.model.Expense;
import com.kartaguez.bluepot.crud.domain.model.Pot;
import com.kartaguez.bluepot.crud.domain.model.PotGlobalVersion;
import com.kartaguez.bluepot.crud.domain.top.dto.AddExpenseDtoIn;
import com.kartaguez.bluepot.crud.domain.top.dto.AddExpenseDtoOut;
import com.kartaguez.bluepot.crud.domain.top.mapper.AddExpenseDtoInMapper;
import com.kartaguez.bluepot.crud.domain.top.mapper.AddExpenseDtoOutMapper;

public class AddExpenseUseCaseWorker {

    private PotGlobalVersionRepository potGlobalVersionRepository;
    private PotRepository potRepository;
    private ExpenseRepository expenseRepository;
    private AddExpenseDtoIn addExpenseDtoIn;
    private AddExpenseDtoInMapper addExpenseDtoInMapper;
    private AddExpenseDtoOutMapper addExpenseDtoOutMapper;
    private PotGlobalVersion potGlobalVersion;
    private Pot pot;
    private Expense expense;

    public static AddExpenseUseCaseWorker getNewInstance(PotGlobalVersionRepository _potGlobalVersionRepository, PotRepository _potRepository, ExpenseRepository _expenseRepository, AddExpenseDtoInMapper _addExpenseDtoInMapper, AddExpenseDtoOutMapper _addExpenseDtoOutMapper) {
        return new AddExpenseUseCaseWorker(_potGlobalVersionRepository, _potRepository, _expenseRepository, _addExpenseDtoInMapper, _addExpenseDtoOutMapper);
    }

    private AddExpenseUseCaseWorker(PotGlobalVersionRepository _potGlobalVersionRepository, PotRepository _potRepository, ExpenseRepository _expenseRepository, AddExpenseDtoInMapper _addExpenseDtoInMapper, AddExpenseDtoOutMapper _addExpenseDtoOutMapper) {
        this.potGlobalVersionRepository = _potGlobalVersionRepository;
        this.potRepository = _potRepository;
        this.expenseRepository = _expenseRepository;
        this.addExpenseDtoInMapper = _addExpenseDtoInMapper;
        this.addExpenseDtoOutMapper = _addExpenseDtoOutMapper;
    }

    public Step1 initiateWorkflow() {
        return new privateStep1();
    }

    private AddExpenseUseCaseWorker withAddExpenseDtoIn(AddExpenseDtoIn _addExpenseDtoIn) {
        this.addExpenseDtoIn = _addExpenseDtoIn;
        return this;
    }

    private AddExpenseUseCaseWorker createExpense() {
        this.potGlobalVersion = this.potGlobalVersionRepository.loadPotGlobalVersionByPotUuid(this.addExpenseDtoInMapper.getPotUuid(addExpenseDtoIn));
        this.pot = this.potRepository.loadPotByUuid(this.addExpenseDtoInMapper.getPotUuid(addExpenseDtoIn));
        this.expense = this.addExpenseDtoInMapper.getAddedExpense(addExpenseDtoIn);
        this.pot.addExpense(this.expense);
        return this;
    }

    private AddExpenseUseCaseWorker saveExpense() {
        this.expenseRepository.save(this.expense);
        this.potGlobalVersionRepository.save(this.potGlobalVersion);
        return this;
    }

    public AddExpenseDtoOut reply() {
        return AddExpenseUseCaseWorker.this.addExpenseDtoOutMapper.getAddExpenseDtoOut(expense);
    }

    // Workflow steps
    public interface Step1 {
        public Step2 withAddExpenseDtoIn(AddExpenseDtoIn _addExpenseDtoIn);
    }

    private class privateStep1 implements Step1 {
        public Step2 withAddExpenseDtoIn(AddExpenseDtoIn _addExpenseDtoIn) {
            AddExpenseUseCaseWorker.this.withAddExpenseDtoIn(_addExpenseDtoIn);
            return new privateStep2();
        }
    }

    public interface Step2 {
        public Step3 createExpense();
    }

    private class privateStep2 implements Step2 {
        public Step3 createExpense() {
            AddExpenseUseCaseWorker.this.createExpense();
            return new privateStep3();
        }
    }

    public interface Step3 {
        public Step4 saveExpense();
    }
    
    private class privateStep3 implements Step3 {
        public Step4 saveExpense() {
            AddExpenseUseCaseWorker.this.saveExpense();
            return new privateStep4();
        }
    }
    
    public interface Step4 {
        public AddExpenseDtoOut reply();
    }

    private class privateStep4 implements Step4 {
        public AddExpenseDtoOut reply() {
            return AddExpenseUseCaseWorker.this.reply();
        }
    }
}
