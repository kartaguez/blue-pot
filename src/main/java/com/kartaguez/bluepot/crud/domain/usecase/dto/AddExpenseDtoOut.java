package com.kartaguez.bluepot.crud.domain.usecase.dto;

import java.util.List;

import com.kartaguez.bluepot.crud.domain.model.dto.ExpenseDto;
import com.kartaguez.bluepot.crud.domain.model.dto.ExpenseShareholderDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddExpenseDtoOut {

    private final ExpenseDto expenseDto;
    private final List<ExpenseShareholderDto> expenseShareholderDtos;

}