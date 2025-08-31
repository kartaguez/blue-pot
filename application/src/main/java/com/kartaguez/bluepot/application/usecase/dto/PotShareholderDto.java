package com.kartaguez.bluepot.application.usecase.dto;

import com.kartaguez.bluepot.domain.model.PotShareholder;

public record PotShareholderDto(String uuid, String name) {

    public static PotShareholderDto of(PotShareholder potShareholder) {
        return new PotShareholderDto(potShareholder.getUuid().toString(), potShareholder.getName());
    }
}
