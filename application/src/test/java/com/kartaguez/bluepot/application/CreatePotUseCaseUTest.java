package com.kartaguez.bluepot.application;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.kartaguez.bluepot.application.usecase.CreatePotUseCase;
import com.kartaguez.bluepot.application.usecase.dto.CreatePotDto;
import com.kartaguez.bluepot.application.usecase.dto.PotDto;
import com.kartaguez.bluepot.application.usecase.dto.PotShareholderDto;


public class CreatePotUseCaseUTest {

    @Test
    public void create_Pot_with_PotShareholders_OK() {
        CreatePotUseCase createPotUseCase = new CreatePotUseCase();
        PotShareholderDto psDto1 = new PotShareholderDto(null, "Alex");
        PotShareholderDto psDto2 = new PotShareholderDto(null, "Bob");
        PotDto potDto = new PotDto(null, "Pot 1", List.of(psDto1, psDto2));
        CreatePotDto createPotDtoIn = new CreatePotDto(potDto);
        createPotUseCase.apply(createPotDtoIn);
    }

    @Test
    public void create_Pot_with_PotShareholders_null_DTO_KO() {
        CreatePotUseCase createPotUseCase = new CreatePotUseCase();
        CreatePotDto createPotDtoIn = null;
        assertThrows(IllegalArgumentException.class, () -> createPotUseCase.apply(createPotDtoIn));
    }

    @Test
    public void create_Pot_with_PotShareholders_null_potName_KO() {
        CreatePotUseCase createPotUseCase = new CreatePotUseCase();
        PotShareholderDto psDto1 = new PotShareholderDto(null, "Alex");
        PotShareholderDto psDto2 = new PotShareholderDto(null, "Bob");
        PotDto potDto = new PotDto(null, null, List.of(psDto1, psDto2));
        CreatePotDto createPotDtoIn = new CreatePotDto(potDto);
        assertThrows(IllegalArgumentException.class, () -> createPotUseCase.apply(createPotDtoIn));
    }

    @Test
    public void create_Pot_with_PotShareholders_empty_potName_KO() {
        CreatePotUseCase createPotUseCase = new CreatePotUseCase();
        PotShareholderDto psDto1 = new PotShareholderDto(null, "Alex");
        PotShareholderDto psDto2 = new PotShareholderDto(null, "Bob");
        PotDto potDto = new PotDto(null, "", List.of(psDto1, psDto2));
        CreatePotDto createPotDtoIn = new CreatePotDto(potDto);
        assertThrows(IllegalArgumentException.class, () -> createPotUseCase.apply(createPotDtoIn));
    }

    @Test
    public void create_Pot_with_PotShareholders_null_potShareholderNames_list_OK() {
        CreatePotUseCase createPotUseCase = new CreatePotUseCase();
        PotDto potDto = new PotDto(null, "Pot 1", null);
        CreatePotDto createPotDtoIn = new CreatePotDto(potDto);
        createPotUseCase.apply(createPotDtoIn);
    }

    @Test
    public void create_Pot_with_PotShareholders_empty_potShareholderNames_list_OK() {
        CreatePotUseCase createPotUseCase = new CreatePotUseCase();
        PotDto potDto = new PotDto(null, "Pot 1", List.of());
        CreatePotDto createPotDtoIn = new CreatePotDto(potDto);
        createPotUseCase.apply(createPotDtoIn);
    }

}
