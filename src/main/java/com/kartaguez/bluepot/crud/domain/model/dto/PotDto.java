package com.kartaguez.bluepot.crud.domain.model.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PotDto {
    private final String uuid;
    private final String name;
}
