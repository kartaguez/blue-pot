package com.kartaguez.bluepot.domain._to_delete.mutation;

import com.kartaguez.bluepot.domain._to_delete.model.ExpenseShareholder;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExpenseShareholderMutationResultSet {
    
    private final ExpenseShareholder ObsoleteExpenseShareholderInstance;
    private final ExpenseShareholder NewExpenseShareholderInstance;

}
