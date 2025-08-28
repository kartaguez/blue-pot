package com.kartaguez.bluepot.application.usecase.dto;

import java.util.List;

import com.kartaguez.bluepot.domain._to_delete.dto.ExpenseDto;
import com.kartaguez.bluepot.domain._to_delete.dto.ExpenseShareholderDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AddExpenseDtoIn {

    private final ExpenseDto expenseDto;
    private final List<ExpenseShareholderDto> expenseShareholderDtos;

}
