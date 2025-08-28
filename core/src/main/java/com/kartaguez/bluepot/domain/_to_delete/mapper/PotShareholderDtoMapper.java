package com.kartaguez.bluepot.domain._to_delete.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.kartaguez.bluepot.domain._to_delete.dto.PotShareholderDto;
import com.kartaguez.bluepot.domain._to_delete.model.PotShareholder_old1;

import lombok.NonNull;

public class PotShareholderDtoMapper {

    public static PotShareholderDto toDto(@NonNull PotShareholder_old1 potShareholder) {
        String tUuid = null;
        if (null != potShareholder.getUuid()) {
            tUuid = potShareholder.getUuid().toString();
        }
        String tPotUuid = null;
        if (null != potShareholder.getPotUuid()) {
            tPotUuid = potShareholder.getPotUuid().toString();
        }

        return new PotShareholderDto(tUuid, tPotUuid, potShareholder.getName());
    }

    public static List<PotShareholderDto> fromHashMapToList(@NonNull HashMap<UUID, PotShareholder_old1> potShareholders) {

        ArrayList<PotShareholderDto> potShareholderDtos = new ArrayList<PotShareholderDto>();
        potShareholders.values().stream().forEach(potShareholderDto -> potShareholderDtos.add(PotShareholderDtoMapper.toDto(potShareholderDto)));

        return potShareholderDtos; 
    }
}
