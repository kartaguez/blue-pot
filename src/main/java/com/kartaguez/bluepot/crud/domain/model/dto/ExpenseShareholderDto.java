package com.kartaguez.bluepot.crud.domain.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExpenseShareholderDto {
        private final String uuid;
        private final String expenseUuid;
        private final String potShareholderUuid;
        private final String weight;
}
