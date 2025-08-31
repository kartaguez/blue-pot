package com.kartaguez.bluepot.application.usecase;

import java.util.ArrayList;
import java.util.List;

import com.kartaguez.bluepot.application.usecase.dto.CreatePotDto;
import com.kartaguez.bluepot.domain.model.Pot;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreatePotUseCase {

    public CreatePotDto apply(CreatePotDto createPotDto) {
        if (null == createPotDto) {
            throw new IllegalArgumentException("CreatePotDto cannot be null.");
        }
        if (null == createPotDto.potDto()) {
            throw new IllegalArgumentException("PotDto cannot be null.");
        }
        
        Pot pot = Pot.builder(createPotDto.potDto().name()).build();
        
        List<String> potShareholderNames = new ArrayList<String>();
        if (null != createPotDto.potDto().potShareholderDtos()) {
            createPotDto.potDto().potShareholderDtos().stream().forEach(potShareholderDto -> potShareholderNames.add(potShareholderDto.name()));
        }
        pot.addPotShareholders(potShareholderNames);

        return CreatePotDto.of(pot);
    }

}
