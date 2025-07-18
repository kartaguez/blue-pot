package com.kartaguez.bluepot.crud.domain.in.worker;

import java.util.UUID;

import org.apache.commons.lang3.math.Fraction;

import com.kartaguez.bluepot.crud.domain.in.dto.AddExpenseDtoIn;
import com.kartaguez.bluepot.crud.domain.in.mapper.AddExpenseDtoInMapper;
import com.kartaguez.bluepot.crud.domain.model.Expense;
import com.kartaguez.bluepot.crud.domain.model.Pot;
import com.kartaguez.bluepot.crud.domain.out.repository.ExpenseRepository;
import com.kartaguez.bluepot.crud.domain.out.repository.PotRepository;

public class AddExpenseUseCaseWorker {

    public PotRepository potRepository;
    public ExpenseRepository expenseRepository;
    public AddExpenseDtoIn addExpenseDtoIn;
    public AddExpenseDtoInMapper addExpenseDtoInMapper;
    public Pot pot;
    public Expense expense;

    public AddExpenseUseCaseWorker(PotRepository _potRepository, ExpenseRepository _expenseRepository, AddExpenseDtoInMapper _addExpenseDtoInMapper) {
        this.potRepository = _potRepository;
        this.expenseRepository = _expenseRepository;
        this.addExpenseDtoInMapper = _addExpenseDtoInMapper;
    }

    public AddExpenseUseCaseWorker withAddExpenseDtoIn(AddExpenseDtoIn _addExpenseDtoIn) {
        this.addExpenseDtoIn = _addExpenseDtoIn;
        return this;
    }


    public AddExpenseUseCaseWorker createExpense() {
        this.pot = this.potRepository.loadPotByUuid(this.addExpenseDtoInMapper.getPotUuid(addExpenseDtoIn));
        this.expense = new Expense(this.pot.getUuid(), addExpenseDtoIn.getLabel(), addExpenseDtoInMapper.getExpenseAmount(addExpenseDtoIn));
        return this;
    }

    public AddExpenseUseCaseWorker saveExpense() {
        this.expenseRepository.save(this.expense);
        return this;
    }
}
