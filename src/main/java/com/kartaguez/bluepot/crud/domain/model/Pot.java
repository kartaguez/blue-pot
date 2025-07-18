package com.kartaguez.bluepot.crud.domain.model;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class Pot {

    private UUID uuid;
    private long id;

    private String name;

    private List<PotShareholder> shareholders;
    private List<Expense> expenses;

    public Pot(String _name) {
        this.uuid = UUID.randomUUID();
        this.name = _name;
        this.shareholders = new ArrayList<PotShareholder>();
        this.expenses = new ArrayList<Expense>();
    }

    public void addPotShareholder(String name) {
        PotShareholder potShareholder = new PotShareholder(this.uuid, name);
        this.shareholders.add(potShareholder);
    }

    public void addExpense(Expense expense) {
        expense.setPotUuid(this.uuid);
        this.expenses.add(expense);
    }
}
