package com.kartaguez.bluepot.crud.domain.usecase.worker;

import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang3.math.Fraction;

import com.kartaguez.bluepot.crud.domain.bottom.repository.ExpenseRepository;
import com.kartaguez.bluepot.crud.domain.bottom.repository.PotGlobalVersionRepository;
import com.kartaguez.bluepot.crud.domain.bottom.repository.PotRepository;
import com.kartaguez.bluepot.crud.domain.model.object.Expense;
import com.kartaguez.bluepot.crud.domain.model.object.Pot;
import com.kartaguez.bluepot.crud.domain.model.object.PotGlobalVersion;
import com.kartaguez.bluepot.crud.domain.usecase.dto.AddExpenseDtoIn;
import com.kartaguez.bluepot.crud.domain.usecase.dto.AddExpenseDtoOut;
import com.kartaguez.bluepot.crud.domain.usecase.mapper.AddExpenseDtoInMapper;
import com.kartaguez.bluepot.crud.domain.usecase.mapper.AddExpenseDtoOutMapper;
import com.kartaguez.bluepot.crud.utils.Constants;

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
        if (null == _addExpenseDtoIn) {
            throw new IllegalArgumentException("Input Add Expense DTO cannot be null.");
        }
        if (null == _addExpenseDtoIn.getExpenseDto()) {
            throw new IllegalArgumentException("Input Add Expense Expense cannot be null.");
        }
        if (null == _addExpenseDtoIn.getExpenseDto().getPotUuid()) {
            throw new IllegalArgumentException("Input Add Expense Pot UUID cannot be null.");
        }
        if (null == _addExpenseDtoIn.getExpenseDto().getPayerUuid()) {
            throw new IllegalArgumentException("Input Add Expense Payer UUID cannot be null.");
        }
        if (null == _addExpenseDtoIn.getExpenseDto().getAmount()) {
            throw new IllegalArgumentException("Input Add Expense Amount cannot be null.");
        } else if (Fraction.ZERO.equals(Fraction.getFraction(_addExpenseDtoIn.getExpenseDto().getAmount()))) {
            throw new IllegalArgumentException("Input Add Expense Amount cannot be 0.");
        }
        if (null == _addExpenseDtoIn.getExpenseDto().getLabel()) {
            throw new IllegalArgumentException("Input Add Expense Label cannot be null.");
        } else if (Constants.EMPTY_STRING.equals(_addExpenseDtoIn.getExpenseDto().getLabel())) {
            throw new IllegalArgumentException("Input Add Expense Amount cannot be 0.");
        }
        return this;
    }

    private AddExpenseUseCaseWorker createExpense() {
        this.potGlobalVersion = this.potGlobalVersionRepository.loadPotGlobalVersionByPotUuid(UUID.fromString(this.addExpenseDtoIn.getExpenseDto().getPotUuid()));
        this.potGlobalVersion.incrementVersion();
        this.pot = this.potRepository.loadPotByUuid(UUID.fromString(this.addExpenseDtoIn.getExpenseDto().getPotUuid()), this.potGlobalVersion.getValue());
       
        HashMap<UUID, Fraction> payeeWeights = this.addExpenseDtoInMapper.getExpenseShareholderDtoMapper().fromListToHashMap(this.addExpenseDtoIn.getExpenseShareholderDtos());
        
        this.pot.addExpense(UUID.fromString(this.addExpenseDtoIn.getExpenseDto().getPayerUuid()), payeeWeights, Fraction.getFraction(this.addExpenseDtoIn.getExpenseDto().getAmount()), this.addExpenseDtoIn.getExpenseDto().getLabel());
        
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
