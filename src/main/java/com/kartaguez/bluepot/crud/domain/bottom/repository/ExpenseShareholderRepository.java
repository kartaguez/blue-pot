package com.kartaguez.bluepot.crud.domain.bottom.repository;

import com.kartaguez.bluepot.crud.domain.model.object.ExpenseShareholder;

import lombok.NonNull;

public interface ExpenseShareholderRepository {

    public void save(@NonNull ExpenseShareholder expenseShareholder);
}
