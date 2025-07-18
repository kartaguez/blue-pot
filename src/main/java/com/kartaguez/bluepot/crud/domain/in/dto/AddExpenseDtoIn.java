package com.kartaguez.bluepot.crud.domain.in.dto;

import java.util.List;

import lombok.Data;

@Data
public class AddExpenseDtoIn {

    private String potUuid;
    private String label;
    private String amount;
    private List<ExpenseShareholderDtoIn> expenseShareholders;

    @Data
    private class ExpenseShareholderDtoIn {
        private String potShareholderUuid;
        private String weight;
    }
}
