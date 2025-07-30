package com.kartaguez.bluepot.crud.domain.model.mapper;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.crud.domain.model.dto.ExpenseDto;
import com.kartaguez.bluepot.crud.domain.model.object.Expense;

import lombok.NonNull;

@Component
public class ExpenseDtoMapper {

    public ExpenseDto toDto(@NonNull Expense expense) {
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
