package com.kartaguez.bluepot.application.usecase.dto;

import com.kartaguez.bluepot.domain.model.Pot;

public record CreatePotDto(PotDto potDto) {

    public static CreatePotDto of(Pot pot) {
        PotDto potDto = PotDto.of(pot);
        return new CreatePotDto(potDto);
    }

}
