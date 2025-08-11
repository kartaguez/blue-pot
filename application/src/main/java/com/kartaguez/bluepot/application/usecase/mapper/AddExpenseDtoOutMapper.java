package com.kartaguez.bluepot.application.usecase.mapper;

import com.kartaguez.bluepot.application.usecase.dto.AddExpenseDtoOut;
import com.kartaguez.bluepot.domain.mapper.ExpenseDtoMapper;
import com.kartaguez.bluepot.domain.mapper.ExpenseShareholderDtoMapper;
import com.kartaguez.bluepot.domain.model.Expense;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddExpenseDtoOutMapper {

    public static AddExpenseDtoOut getAddExpenseDtoOut(@NonNull Expense expense) {

        return new AddExpenseDtoOut(ExpenseDtoMapper.toDto(expense), ExpenseShareholderDtoMapper.fromHashMapToList(expense.getExpenseShareholders()));
   
    }
}
