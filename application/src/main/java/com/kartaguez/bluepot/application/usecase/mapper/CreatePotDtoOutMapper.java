package com.kartaguez.bluepot.application.usecase.mapper;

import com.kartaguez.bluepot.application.usecase.dto.CreatePotDtoOut;
import com.kartaguez.bluepot.domain.mapper.PotDtoMapper;
import com.kartaguez.bluepot.domain.mapper.PotShareholderDtoMapper;
import com.kartaguez.bluepot.domain.model.Pot;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreatePotDtoOutMapper {
        
    public static CreatePotDtoOut getCreatePotDtoOut(@NonNull Pot pot) {

        return new CreatePotDtoOut(PotDtoMapper.toDto(pot), PotShareholderDtoMapper.fromHashMapToList(pot.getPotShareholders()));

    }

}
