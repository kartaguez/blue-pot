package com.kartaguez.bluepot.crud.domain.model.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.math.Fraction;
import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.crud.domain.model.dto.ExpenseShareholderDto;
import com.kartaguez.bluepot.crud.domain.model.object.ExpenseShareholder;

import lombok.NonNull;

@Component
public class ExpenseShareholderDtoMapper {

    public ExpenseShareholderDto toDto(@NonNull ExpenseShareholder expenseShareholder) {
        String tUuid = null;
        if (null != expenseShareholder.getUuid()) {
            tUuid = expenseShareholder.getUuid().toString();
        }
        String tExpenseUuid = null;
        if (null != expenseShareholder.getExpenseUuid()) {
            tExpenseUuid = expenseShareholder.getExpenseUuid().toString();
        }
        String tPotShareholderUuid = null;
        if (null != expenseShareholder.getPotShareholderUuid()) {
            tPotShareholderUuid = expenseShareholder.getPotShareholderUuid().toString();
        }
        String tWeight = null;
        if (null != expenseShareholder.getWeight()) {
            tWeight = expenseShareholder.getWeight().toString();
        }
        return new ExpenseShareholderDto(tUuid, tExpenseUuid, tPotShareholderUuid, tWeight);
    }
    
    public HashMap<UUID, Fraction> fromListToHashMap(@NonNull List<ExpenseShareholderDto> expenseShareholderDtos) {
        HashMap<UUID, Fraction> payeeWeights = new HashMap<UUID, Fraction>();
        expenseShareholderDtos.stream().forEach(expenseShareholderDto -> {
            if (null == expenseShareholderDto) {
                throw new IllegalArgumentException("Expense Shareholder DTO cannot be null.");
            }
            if (null == expenseShareholderDto.getPotShareholderUuid()) {
                throw new IllegalArgumentException("Expense Shareholder PotShareholder UUID cannot be null.");
            }
            if (null == expenseShareholderDto.getWeight()) {
                throw new IllegalArgumentException("Expense Shareholder Weight cannot be null.");
            }
            if (null != payeeWeights.get(UUID.fromString(expenseShareholderDto.getPotShareholderUuid()))) {
                throw new IllegalArgumentException("Duplicate Pot Shareholder Uuid for Expense shareholders.");
            }
            payeeWeights.put(UUID.fromString(expenseShareholderDto.getPotShareholderUuid()), Fraction.getFraction(expenseShareholderDto.getWeight()));
        }); 
        return payeeWeights; 
    }

    public List<ExpenseShareholderDto> fromHashMapToList(@NonNull HashMap<UUID, ExpenseShareholder> expenseShareholders) {

        ArrayList<ExpenseShareholderDto> expenseShareholderDtos = new ArrayList<ExpenseShareholderDto>();
        expenseShareholders.values().stream().forEach(potShareholderDto -> expenseShareholderDtos.add(this.toDto(potShareholderDto)));

        return expenseShareholderDtos; 
    }

}
