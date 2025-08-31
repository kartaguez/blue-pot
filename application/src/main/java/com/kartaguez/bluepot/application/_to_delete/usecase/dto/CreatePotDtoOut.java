package com.kartaguez.bluepot.application._to_delete.usecase.dto;

import java.util.List;

import com.kartaguez.bluepot.domain._to_delete.dto.PotDto;
import com.kartaguez.bluepot.domain._to_delete.dto.PotShareholderDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class CreatePotDtoOut {

    private final PotDto PotDto;
    private final List<PotShareholderDto> potShareholderDtos;

}
