package com.kartaguez.bluepot.application.usecase.dto;

import java.util.List;

import com.kartaguez.bluepot.domain._to_delete.dto.PotDto;
import com.kartaguez.bluepot.domain._to_delete.dto.PotShareholderDto;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@RequiredArgsConstructor
@ToString
public class CreatePotDtoIn {

    private final PotDto PotDto;
    private final List<PotShareholderDto> potShareholderDtos;

}
