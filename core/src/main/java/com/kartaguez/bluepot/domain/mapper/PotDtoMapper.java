package com.kartaguez.bluepot.domain.mapper;

import com.kartaguez.bluepot.domain.dto.PotDto;
import com.kartaguez.bluepot.domain.model.Pot;

import lombok.NonNull;

public class PotDtoMapper {

    public static PotDto toDto(@NonNull Pot pot) {
        String tUuid = null;
        if (null != pot.getUuid()) {
            tUuid = pot.getUuid().toString();
        }
        return new PotDto(tUuid, pot.getName());
    }
    
}
