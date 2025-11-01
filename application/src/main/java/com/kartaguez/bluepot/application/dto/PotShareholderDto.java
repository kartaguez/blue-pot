package com.kartaguez.bluepot.application.dto;

import com.kartaguez.bluepot.model.PotShareholder;

public record PotShareholderDto(String uuid, String name) {

    public static PotShareholderDto of(PotShareholder potShareholder) {
        return new PotShareholderDto(potShareholder.getUuid().toString(), potShareholder.getName());
    }
}
