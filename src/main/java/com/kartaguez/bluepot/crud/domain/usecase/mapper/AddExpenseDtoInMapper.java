package com.kartaguez.bluepot.crud.domain.usecase.mapper;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.crud.domain.model.mapper.ExpenseShareholderDtoMapper;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Component
@Data
@RequiredArgsConstructor
public class AddExpenseDtoInMapper {

    private final ExpenseShareholderDtoMapper expenseShareholderDtoMapper;

}
