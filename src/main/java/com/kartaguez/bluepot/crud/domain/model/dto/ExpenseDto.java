package com.kartaguez.bluepot.crud.domain.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExpenseDto {
    private final String uuid;
    private final String potUuid;
    private final String payerUuid;

    private final String label;
    private final String amount;
}
