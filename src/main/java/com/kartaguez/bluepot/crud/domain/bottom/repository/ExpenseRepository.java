package com.kartaguez.bluepot.crud.domain.bottom.repository;

import com.kartaguez.bluepot.crud.domain.model.object.Expense;

public interface ExpenseRepository {

    public void save(Expense expense);
}
