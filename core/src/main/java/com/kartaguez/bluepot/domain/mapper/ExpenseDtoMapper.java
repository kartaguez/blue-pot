package com.kartaguez.bluepot.domain.mapper;

import com.kartaguez.bluepot.domain.dto.ExpenseDto;
import com.kartaguez.bluepot.domain.model.Expense;

import lombok.NonNull;

public class ExpenseDtoMapper {

    public static ExpenseDto toDto(@NonNull Expense expense) {
        String tUuid = null;
        if (null != expense.getUuid()) {
            tUuid = expense.getUuid().toString();
        }
        String tPotUuid = null;
        if (null != expense.getPotUuid()) {
            tPotUuid = expense.getPotUuid().toString();
        }
        String tPayerUuid = null;
        if (null != expense.getPayerUuid()) {
            tPayerUuid = expense.getPayerUuid().toString();
        }
        String tAmount = null;
        if (null != expense.getAmount()) {
            tAmount = expense.getAmount().toString();
        }
        return new ExpenseDto(tUuid, tPotUuid, tPayerUuid, expense.getLabel(), tAmount);
    }

}
