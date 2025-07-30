package com.kartaguez.bluepot.crud.domain.model.mapper;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.crud.domain.model.dto.PotDto;
import com.kartaguez.bluepot.crud.domain.model.object.Pot;

import lombok.NonNull;

@Component
public class PotDtoMapper {

    public PotDto toDto(@NonNull Pot pot) {
        String tUuid = null;
        if (null != pot.getUuid()) {
            tUuid = pot.getUuid().toString();
        }
        return new PotDto(tUuid, pot.getName());
    }
    
}
