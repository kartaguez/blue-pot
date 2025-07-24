package com.kartaguez.bluepot.crud.domain.top.mapper;

import java.util.UUID;

import org.apache.commons.lang3.math.Fraction;

import com.kartaguez.bluepot.crud.domain.model.Expense;
import com.kartaguez.bluepot.crud.domain.top.dto.AddExpenseDtoIn;
import com.kartaguez.bluepot.crud.domain.top.dto.ExpenseShareholderDtoIn;

import lombok.NonNull;

public class AddExpenseDtoInMapper {

    public UUID getPotUuid(@NonNull AddExpenseDtoIn addExpenseDtoIn) {
        return UUID.fromString(addExpenseDtoIn.getExpenseDtoIn().getPotUuid());
    }

    public Expense getAddedExpense(@NonNull AddExpenseDtoIn addExpenseDtoIn) {
        Expense expense = new Expense(UUID.fromString(addExpenseDtoIn.getExpenseDtoIn().getPotUuid()), addExpenseDtoIn.getExpenseDtoIn().getLabel(), Fraction.getFraction(addExpenseDtoIn.getExpenseDtoIn().getAmount()));
        for (ExpenseShareholderDtoIn expenseShareholderDtoIn : addExpenseDtoIn.getExpenseShareholderDtoIns()) {
            expense.addShareholder(UUID.fromString(expenseShareholderDtoIn.getPotShareholderUuid()), Fraction.getFraction(expenseShareholderDtoIn.getWeight()));
        }
        return expense;
    }

}
