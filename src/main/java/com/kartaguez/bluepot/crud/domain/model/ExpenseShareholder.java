package com.kartaguez.bluepot.crud.domain.model;

import java.util.UUID;

import org.apache.commons.lang3.math.Fraction;

import lombok.Data;

@Data
public class ExpenseShareholder {

    private UUID uuid;
    private long id;

    public UUID potShareholderUuid;
    public UUID expenseUuid;
    
    public Fraction weight;

    public ExpenseShareholder(UUID _expenseUuid, UUID _potShareholderUuid, Fraction _weight) {
        this.uuid = UUID.randomUUID();
        this.expenseUuid = _expenseUuid;
        this.potShareholderUuid = _potShareholderUuid;
    }
}
