package com.kartaguez.bluepot.crud.domain.model.mutation;

import java.util.List;

import com.kartaguez.bluepot.crud.domain.model.object.Expense;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExpenseMutationResultSet {

    private final Expense ObsoleteExpenseInstance;
    private final Expense NewExpenseInstance;
    
    private final List<ExpenseShareholderMutationResultSet> expenseShareholderMutationResultSet;
}
