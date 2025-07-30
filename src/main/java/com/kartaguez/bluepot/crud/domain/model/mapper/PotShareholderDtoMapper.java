package com.kartaguez.bluepot.crud.domain.model.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.crud.domain.model.dto.PotShareholderDto;
import com.kartaguez.bluepot.crud.domain.model.object.PotShareholder;

import lombok.NonNull;

@Component
public class PotShareholderDtoMapper {

    public PotShareholderDto toDto(@NonNull PotShareholder potShareholder) {
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

    public List<PotShareholderDto> fromHashMapToList(@NonNull HashMap<UUID, PotShareholder> potShareholders) {

        ArrayList<PotShareholderDto> potShareholderDtos = new ArrayList<PotShareholderDto>();
        potShareholders.values().stream().forEach(potShareholderDto -> potShareholderDtos.add(this.toDto(potShareholderDto)));

        return potShareholderDtos; 
    }
}
