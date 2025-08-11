package com.kartaguez.bluepot.application.down.repository;

import com.kartaguez.bluepot.domain.model.Expense;

public interface ExpenseRepository {

    public Expense save(Expense expense);
}
