package com.kartaguez.bluepot.crud.domain.top.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExpenseDtoIn {

    private final String potUuid;
    private final String label;
    private final String amount;
}
