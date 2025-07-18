package com.kartaguez.bluepot.crud.domain.in.mapper;

import java.util.UUID;

import org.apache.commons.lang3.math.Fraction;

import com.kartaguez.bluepot.crud.domain.in.dto.AddExpenseDtoIn;

public class AddExpenseDtoInMapper {

    public UUID getPotUuid(AddExpenseDtoIn addExpenseDtoIn) {
        
        return UUID.fromString(addExpenseDtoIn.getPotUuid());

    }

    public Fraction getExpenseAmount(AddExpenseDtoIn addExpenseDtoIn) {
        
        return Fraction.getFraction(addExpenseDtoIn.getAmount());

    }



}
