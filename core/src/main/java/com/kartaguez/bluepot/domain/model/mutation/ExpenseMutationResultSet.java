package com.kartaguez.bluepot.domain.model.mutation;

import java.util.List;

import com.kartaguez.bluepot.domain.model.Expense;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExpenseMutationResultSet {

    private final Expense ObsoleteExpenseInstance;
    private final Expense NewExpenseInstance;
    
    private final List<ExpenseShareholderMutationResultSet> expenseShareholderMutationResultSet;
}
