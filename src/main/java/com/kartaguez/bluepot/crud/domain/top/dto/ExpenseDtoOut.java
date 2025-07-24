package com.kartaguez.bluepot.crud.domain.top.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExpenseDtoOut {

    private final String uuid;
    private final String potUuid;
    private final String label;
    private final String amount;
}
