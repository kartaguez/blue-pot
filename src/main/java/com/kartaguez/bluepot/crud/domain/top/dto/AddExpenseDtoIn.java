package com.kartaguez.bluepot.crud.domain.top.dto;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddExpenseDtoIn {

    private final ExpenseDtoIn expenseDtoIn;
    private final List<ExpenseShareholderDtoIn> expenseShareholderDtoIns;

}
