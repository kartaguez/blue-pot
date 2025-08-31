package com.kartaguez.bluepot.application._to_delete.down.repository;

import com.kartaguez.bluepot.domain._to_delete.model.Expense;

public interface ExpenseRepository {

    public Expense save(Expense expense);
}
