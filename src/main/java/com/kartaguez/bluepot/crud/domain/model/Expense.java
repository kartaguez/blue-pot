package com.kartaguez.bluepot.crud.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.math.Fraction;

import lombok.Data;

@Data
public class Expense {

    private UUID uuid;
    private long id;    

    private UUID potUuid;

    private String label;
    private Fraction amount;

    private List<ExpenseShareholder> expenseShareholders;

    public Expense(UUID _potUuid, String _label, Fraction _amount) {
        this.uuid = UUID.randomUUID();
        this.potUuid = _potUuid;
        this.label = _label;
        this.amount = _amount;
        this.expenseShareholders = new ArrayList<ExpenseShareholder>();
    }

    public void addShareholder(UUID potShareholderUuid, Fraction weight) {
        ExpenseShareholder expenseShareholder = new ExpenseShareholder(this.uuid, potShareholderUuid, weight);
        this.expenseShareholders.add(expenseShareholder);
    }

}
