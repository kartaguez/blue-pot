package com.kartaguez.bluepot.crud.domain.model.mutation;

import com.kartaguez.bluepot.crud.domain.model.ExpenseShareholder;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExpenseShareholderMutationResultSet {
    
    private final ExpenseShareholder ObsoleteExpenseShareholderInstance;
    private final ExpenseShareholder NewExpenseShareholderInstance;

}
