package com.kartaguez.bluepot.crud.domain.in;

import com.kartaguez.bluepot.crud.domain.in.dto.AddExpenseDtoIn;
import com.kartaguez.bluepot.crud.domain.in.worker.factory.AddExpenseUserCaseWorkerFactory;

public class AddExpenseUseCase {

    AddExpenseUserCaseWorkerFactory addExpenseUserCaseWorkerFactory;

    public void apply(AddExpenseDtoIn addExpenseDtoIn) {

        this.addExpenseUserCaseWorkerFactory.getNewWorker()
            .withAddExpenseDtoIn(addExpenseDtoIn)
            .createExpense()
            .saveExpense();

    }

}
