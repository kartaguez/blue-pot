package com.kartaguez.bluepot.crud.domain.usecase.mapper;

import com.kartaguez.bluepot.crud.domain.model.mapper.ExpenseShareholderDtoMapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddExpenseDtoInMapper {

    private final ExpenseShareholderDtoMapper expenseShareholderDtoMapper;

}
