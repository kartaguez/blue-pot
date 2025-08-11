package com.kartaguez.bluepot.application.usecase.worker;

import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang3.math.Fraction;

import com.kartaguez.bluepot.application.down.repository.ExpenseRepository;
import com.kartaguez.bluepot.application.down.repository.PotGlobalVersionRepository;
import com.kartaguez.bluepot.application.down.repository.PotRepository;
import com.kartaguez.bluepot.application.usecase.dto.AddExpenseDtoIn;
import com.kartaguez.bluepot.application.usecase.dto.AddExpenseDtoOut;
import com.kartaguez.bluepot.application.usecase.mapper.AddExpenseDtoOutMapper;
import com.kartaguez.bluepot.domain.mapper.ExpenseShareholderDtoMapper;
import com.kartaguez.bluepot.domain.model.Expense;
import com.kartaguez.bluepot.domain.model.Pot;
import com.kartaguez.bluepot.domain.model.PotGlobalVersion;
import com.kartaguez.bluepot.utils.Constants;

public class AddExpenseUseCaseWorker {

    private PotGlobalVersionRepository potGlobalVersionRepository;
    private PotRepository potRepository;
    private ExpenseRepository expenseRepository;
    private AddExpenseDtoIn addExpenseDtoIn;
    private PotGlobalVersion potGlobalVersion;
    private Pot pot;
    private Expense expense;

    public static AddExpenseUseCaseWorker getNewInstance(PotGlobalVersionRepository _potGlobalVersionRepository, PotRepository _potRepository, ExpenseRepository _expenseRepository) {
        return new AddExpenseUseCaseWorker(_potGlobalVersionRepository, _potRepository, _expenseRepository);
    }

    private AddExpenseUseCaseWorker(PotGlobalVersionRepository _potGlobalVersionRepository, PotRepository _potRepository, ExpenseRepository _expenseRepository) {
        this.potGlobalVersionRepository = _potGlobalVersionRepository;
        this.potRepository = _potRepository;
        this.expenseRepository = _expenseRepository;
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
        this.pot = this.potRepository.loadPotByUuid(UUID.fromString(this.addExpenseDtoIn.getExpenseDto().getPotUuid()), this.potGlobalVersion.getPotVersion());
       
        HashMap<UUID, Fraction> payeeWeights = ExpenseShareholderDtoMapper.fromListToHashMap(this.addExpenseDtoIn.getExpenseShareholderDtos());
        
        this.pot.addExpense(UUID.fromString(this.addExpenseDtoIn.getExpenseDto().getPayerUuid()), payeeWeights, Fraction.getFraction(this.addExpenseDtoIn.getExpenseDto().getAmount()), this.addExpenseDtoIn.getExpenseDto().getLabel());
        
        return this;
    }

    private AddExpenseUseCaseWorker saveExpense() {
        this.expenseRepository.save(this.expense);
        this.potGlobalVersionRepository.save(this.potGlobalVersion);
        return this;
    }

    public AddExpenseDtoOut reply() {
        return AddExpenseDtoOutMapper.getAddExpenseDtoOut(expense);
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
