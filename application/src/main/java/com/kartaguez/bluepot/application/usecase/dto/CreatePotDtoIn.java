package com.kartaguez.bluepot.application.usecase.dto;

import java.util.List;

import com.kartaguez.bluepot.domain.dto.PotDto;
import com.kartaguez.bluepot.domain.dto.PotShareholderDto;

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
