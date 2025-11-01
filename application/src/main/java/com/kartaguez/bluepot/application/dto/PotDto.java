package com.kartaguez.bluepot.application.dto;

import java.util.ArrayList;
import java.util.List;

import com.kartaguez.bluepot.model.Pot;

public record PotDto(String uuid, String name, List<PotShareholderDto> potShareholderDtos) {

    public static PotDto of(Pot pot) {
        List<PotShareholderDto> potShareholderDtos = new ArrayList<PotShareholderDto>();
        pot.getPotShareholders().values().stream().forEach(potShareholder -> potShareholderDtos.add(PotShareholderDto.of(potShareholder)));
        return new PotDto(pot.getUuid().toString(), pot.getName(), potShareholderDtos);
    }
}
