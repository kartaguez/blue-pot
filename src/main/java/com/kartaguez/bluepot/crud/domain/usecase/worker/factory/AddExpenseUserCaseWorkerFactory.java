package com.kartaguez.bluepot.crud.domain.usecase.worker.factory;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.crud.domain.bottom.repository.ExpenseRepository;
import com.kartaguez.bluepot.crud.domain.bottom.repository.PotGlobalVersionRepository;
import com.kartaguez.bluepot.crud.domain.bottom.repository.PotRepository;
import com.kartaguez.bluepot.crud.domain.usecase.mapper.AddExpenseDtoOutMapper;
import com.kartaguez.bluepot.crud.domain.usecase.worker.AddExpenseUseCaseWorker;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AddExpenseUserCaseWorkerFactory {

    private final PotGlobalVersionRepository potGlobalversionRepository;
    private final PotRepository potRepository;
    private final ExpenseRepository expenseRepository;
    private final AddExpenseDtoOutMapper addExpenseDtoOutMapper;

    public AddExpenseUseCaseWorker getNewWorker() {
        return AddExpenseUseCaseWorker.getNewInstance(this.potGlobalversionRepository, this.potRepository, this.expenseRepository, this.addExpenseDtoOutMapper);
    }
}
