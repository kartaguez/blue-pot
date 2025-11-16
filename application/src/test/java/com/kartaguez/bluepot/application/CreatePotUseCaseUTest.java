package com.kartaguez.bluepot.application;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.kartaguez.bluepot.application.usecases.CreatePotUseCase;
import com.kartaguez.bluepot.application.usecases.commands.CreatePotUseCaseCmd;


public class CreatePotUseCaseUTest {

    @Test
    public void create_Pot_with_PotShareholders_OK() {
        CreatePotUseCase createPotUseCase = new CreatePotUseCase();
        CreatePotUseCaseCmd createPotUseCaseCmd = new CreatePotUseCaseCmd("Pot 1", List.of("Alex", "Bob"));
        createPotUseCase.apply(createPotUseCaseCmd);
    }

    @Test
    public void create_Pot_with_cmd_null_KO() {
        CreatePotUseCase createPotUseCase = new CreatePotUseCase();
        CreatePotUseCaseCmd createPotUseCaseCmd = null;
        assertThrows(IllegalArgumentException.class, () -> createPotUseCase.apply(createPotUseCaseCmd));
        createPotUseCase.apply(createPotUseCaseCmd);
    }

    @Test
    public void create_Pot_with_PotName_null_KO() {
        CreatePotUseCase createPotUseCase = new CreatePotUseCase();
        CreatePotUseCaseCmd createPotUseCaseCmd = new CreatePotUseCaseCmd(null, List.of("Alex", "Bob"));
        assertThrows(IllegalArgumentException.class, () -> createPotUseCase.apply(createPotUseCaseCmd));
        createPotUseCase.apply(createPotUseCaseCmd);
    }

    @Test
    public void create_Pot_with_PotShareholders_null_KO() {
        CreatePotUseCase createPotUseCase = new CreatePotUseCase();
        CreatePotUseCaseCmd createPotUseCaseCmd = new CreatePotUseCaseCmd("Pot 1", null);
        assertThrows(IllegalArgumentException.class, () -> createPotUseCase.apply(createPotUseCaseCmd));
    }

    @Test
    public void create_Pot_with_PotShareholders_name_null_KO() {
        CreatePotUseCase createPotUseCase = new CreatePotUseCase();
        CreatePotUseCaseCmd createPotUseCaseCmd = new CreatePotUseCaseCmd("Pot 1", List.of("Alex", null));
        assertThrows(IllegalArgumentException.class, () -> createPotUseCase.apply(createPotUseCaseCmd));
    }

}
