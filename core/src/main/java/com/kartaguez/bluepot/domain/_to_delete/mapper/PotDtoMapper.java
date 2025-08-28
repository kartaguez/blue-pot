package com.kartaguez.bluepot.domain._to_delete.mapper;

import com.kartaguez.bluepot.domain._to_delete.dto.PotDto;
import com.kartaguez.bluepot.domain._to_delete.model.Pot_old1;

import lombok.NonNull;

public class PotDtoMapper {

    public static PotDto toDto(@NonNull Pot_old1 pot) {
        String tUuid = null;
        if (null != pot.getUuid()) {
            tUuid = pot.getUuid().toString();
        }
        return new PotDto(tUuid, pot.getName());
    }
    
}
