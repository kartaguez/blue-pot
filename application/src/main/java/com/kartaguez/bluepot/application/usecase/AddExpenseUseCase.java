package com.kartaguez.bluepot.application.usecase;

import com.kartaguez.bluepot.application.down.repository.ExpenseRepository;
import com.kartaguez.bluepot.application.down.repository.PotGlobalVersionRepository;
import com.kartaguez.bluepot.application.down.repository.PotRepository;
import com.kartaguez.bluepot.application.usecase.dto.AddExpenseDtoIn;
import com.kartaguez.bluepot.application.usecase.dto.AddExpenseDtoOut;
import com.kartaguez.bluepot.application.usecase.worker.AddExpenseUseCaseWorker;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AddExpenseUseCase {

    private final PotGlobalVersionRepository potGlobalversionRepository;
    private final PotRepository potRepository;
    private final ExpenseRepository expenseRepository;

    public AddExpenseDtoOut apply(AddExpenseDtoIn addExpenseDtoIn) {

        return AddExpenseUseCaseWorker.getNewInstance(this.potGlobalversionRepository, this.potRepository, this.expenseRepository)
            .initiateWorkflow()
            .withAddExpenseDtoIn(addExpenseDtoIn)
            .createExpense()
            .saveExpense()
            .reply();

    }

}
