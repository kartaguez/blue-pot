package com.kartaguez.bluepot.crud.domain.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kartaguez.bluepot.crud.domain.usecase.dto.AddExpenseDtoIn;
import com.kartaguez.bluepot.crud.domain.usecase.dto.AddExpenseDtoOut;
import com.kartaguez.bluepot.crud.domain.usecase.worker.factory.AddExpenseUserCaseWorkerFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AddExpenseUseCase {

    private final AddExpenseUserCaseWorkerFactory addExpenseUserCaseWorkerFactory;

    @Transactional
    public AddExpenseDtoOut apply(AddExpenseDtoIn addExpenseDtoIn) {

        return this.addExpenseUserCaseWorkerFactory.getNewWorker()
            .initiateWorkflow()
            .withAddExpenseDtoIn(addExpenseDtoIn)
            .createExpense()
            .saveExpense()
            .reply();

    }

}
