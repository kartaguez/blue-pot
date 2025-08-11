package com.kartaguez.bluepot.application.usecase.dto;

import java.util.List;

import com.kartaguez.bluepot.domain.dto.ExpenseDto;
import com.kartaguez.bluepot.domain.dto.ExpenseShareholderDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddExpenseDtoOut {

    private final ExpenseDto expenseDto;
    private final List<ExpenseShareholderDto> expenseShareholderDtos;

}