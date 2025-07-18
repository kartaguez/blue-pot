package com.kartaguez.bluepot.crud.domain.in.worker.factory;

import com.kartaguez.bluepot.crud.domain.in.mapper.AddExpenseDtoInMapper;
import com.kartaguez.bluepot.crud.domain.in.worker.AddExpenseUseCaseWorker;
import com.kartaguez.bluepot.crud.domain.out.repository.ExpenseRepository;
import com.kartaguez.bluepot.crud.domain.out.repository.PotRepository;

public class AddExpenseUserCaseWorkerFactory {

    private PotRepository potRepository;
    private ExpenseRepository expenseRepository;
    private AddExpenseDtoInMapper addExpenseDtoInMapper;

    public AddExpenseUseCaseWorker getNewWorker() {
        return new AddExpenseUseCaseWorker(this.potRepository, this.expenseRepository, this.addExpenseDtoInMapper);
    }
}
