package com.kartaguez.bluepot.crud.domain.usecase.mapper;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.crud.domain.model.mapper.ExpenseDtoMapper;
import com.kartaguez.bluepot.crud.domain.model.mapper.ExpenseShareholderDtoMapper;
import com.kartaguez.bluepot.crud.domain.model.object.Expense;
import com.kartaguez.bluepot.crud.domain.usecase.dto.AddExpenseDtoOut;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@Data
@RequiredArgsConstructor
public class AddExpenseDtoOutMapper {

    private final ExpenseDtoMapper expenseDtoMapper;
    private final ExpenseShareholderDtoMapper expenseShareholderDtoMapper;

    public AddExpenseDtoOut getAddExpenseDtoOut(@NonNull Expense expense) {

        return new AddExpenseDtoOut(this.getExpenseDtoMapper().toDto(expense), this.expenseShareholderDtoMapper.fromHashMapToList(expense.getExpenseShareholders()));
   
    }
}
