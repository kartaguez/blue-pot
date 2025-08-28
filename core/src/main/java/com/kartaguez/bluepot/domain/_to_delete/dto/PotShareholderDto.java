package com.kartaguez.bluepot.domain._to_delete.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PotShareholderDto {
        private final String uuid;
        private final String potUuid;
        private final String name;
}
