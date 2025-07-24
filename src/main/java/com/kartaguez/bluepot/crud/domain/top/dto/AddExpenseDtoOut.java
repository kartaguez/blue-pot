package com.kartaguez.bluepot.crud.domain.top.dto;

import java.util.List;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddExpenseDtoOut {

    private final ExpenseDtoOut expenseDtoOut;
    private final List<ExpenseShareholderDtoOut> expenseShareholderDtoOuts;

}