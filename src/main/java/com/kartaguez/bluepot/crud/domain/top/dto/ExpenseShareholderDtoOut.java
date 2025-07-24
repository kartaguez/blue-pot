package com.kartaguez.bluepot.crud.domain.top.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExpenseShareholderDtoOut {
        private final String uuid;
        private final String potShareholderUuid;
        private final String weight;
}
