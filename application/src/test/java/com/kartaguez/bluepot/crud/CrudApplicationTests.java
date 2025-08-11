package com.kartaguez.bluepot.crud;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;

import com.kartaguez.bluepot.crud.application.usecase.CreatePotUseCase;
import com.kartaguez.bluepot.crud.application.usecase.dto.CreatePotDtoIn;
import com.kartaguez.bluepot.crud.domain.dto.PotDto;
import com.kartaguez.bluepot.crud.domain.dto.PotShareholderDto;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class CrudApplicationTests {

	private final CreatePotUseCase createPotUseCase;

	@Test
	void contextLoads() {
	}
	
	@Test
	void check_CreatePotUseCase() {
		PotDto potDto = new PotDto(null, "Pot 1");
		ArrayList<PotShareholderDto> potShareholders = new ArrayList<PotShareholderDto>();
		potShareholders.add(new PotShareholderDto(null, null, "PS 1"));
		potShareholders.add(new PotShareholderDto(null, null, "PS 2"));
		CreatePotDtoIn createPotDtoIn = new CreatePotDtoIn(potDto, potShareholders);
		assertNotNull(this.createPotUseCase.apply(createPotDtoIn));
	}

}
