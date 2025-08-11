package com.kartaguez.bluepot.application.down.repository;

import com.kartaguez.bluepot.domain.model.ExpenseShareholder;

import lombok.NonNull;

public interface ExpenseShareholderRepository {

    public ExpenseShareholder save(@NonNull ExpenseShareholder expenseShareholder);
}
