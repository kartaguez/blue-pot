package com.kartaguez.bluepot.crud.domain.usecase.mapper;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.crud.domain.model.mapper.PotDtoMapper;
import com.kartaguez.bluepot.crud.domain.model.mapper.PotShareholderDtoMapper;
import com.kartaguez.bluepot.crud.domain.model.object.Pot;
import com.kartaguez.bluepot.crud.domain.usecase.dto.CreatePotDtoOut;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@Data
@RequiredArgsConstructor
public class CreatePotDtoOutMapper {

    private final PotDtoMapper potDtoMapper;
    private final PotShareholderDtoMapper potShareholderDtoMapper;
        
    public CreatePotDtoOut getCreatePotDtoOut(@NonNull Pot pot) {

        return new CreatePotDtoOut(this.getPotDtoMapper().toDto(pot), this.getPotShareholderDtoMapper().fromHashMapToList(pot.getPotShareholders()));

    }

}
