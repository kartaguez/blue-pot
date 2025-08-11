package com.kartaguez.bluepot.application.usecase.dto;

import java.util.List;

import com.kartaguez.bluepot.domain.dto.PotDto;
import com.kartaguez.bluepot.domain.dto.PotShareholderDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreatePotDtoOut {

    private final PotDto PotDto;
    private final List<PotShareholderDto> potShareholderDtos;

}
