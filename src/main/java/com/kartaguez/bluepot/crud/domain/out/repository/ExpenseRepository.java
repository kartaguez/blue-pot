package com.kartaguez.bluepot.crud.domain.out.repository;

import com.kartaguez.bluepot.crud.domain.model.Expense;

public interface ExpenseRepository {

    public void save(Expense expense);
}
