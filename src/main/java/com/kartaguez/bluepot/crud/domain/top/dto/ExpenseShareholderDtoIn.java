package com.kartaguez.bluepot.crud.domain.top.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExpenseShareholderDtoIn {
        private final String potShareholderUuid;
        private final String weight;
}
