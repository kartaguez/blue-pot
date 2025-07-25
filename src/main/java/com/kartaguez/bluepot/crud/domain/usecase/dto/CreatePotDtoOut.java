package com.kartaguez.bluepot.crud.domain.usecase.dto;

import java.util.List;

import com.kartaguez.bluepot.crud.domain.model.dto.PotDto;
import com.kartaguez.bluepot.crud.domain.model.dto.PotShareholderDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreatePotDtoOut {

    private final PotDto PotDto;
    private final List<PotShareholderDto> potShareholderDtos;

}
