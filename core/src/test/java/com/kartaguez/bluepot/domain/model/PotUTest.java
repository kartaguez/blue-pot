package com.kartaguez.bluepot.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class PotUTest {

    @Test
    public void create_Pot_with_name() {
        String potName = "Pot 1";
        Pot pot = Pot.builder(potName).build();
        assertNotNull(pot);
        assertEquals(pot.getName(), potName);
    }

    @Test
    public void create_Pot_with_name_null_KO() {
        String potName = null;
        assertThrows(IllegalArgumentException.class, () -> Pot.builder(potName));
    }

    @Test
    public void create_Pot_with_name_empty_KO() {
        String potName = "";
        assertThrows(IllegalArgumentException.class, () -> Pot.builder(potName));
    }

    @Test
    public void create_Pot_with_name_blank_KO() {
        String potName = " ";
        assertThrows(IllegalArgumentException.class, () -> Pot.builder(potName));
    }

    @Test
    public void create_Pot_with_trimmable_name_OK() {
        String potName = " Pot 1 ";
        String trimmedPotName = "Pot 1";
        Pot pot = Pot.builder(potName).build();
        assertEquals(pot.getName(), trimmedPotName);
    }

    @Test
    public void create_Pot_with_name_too_long_KO() {
        String potName = "01234567890123456789012345678901234567890";
        assertThrows(IllegalArgumentException.class, () -> Pot.builder(potName));
    }

    @Test
    public void create_Pot_with_name_max_length_OK() {
        String potName = "0123456789012345678901234567890123456789";
        Pot pot = Pot.builder(potName).build();
        assertEquals(pot.getName(), potName);
    }

    @Test
    public void create_Pot_with_trimmed_name_max_length_OK() {
        String potName = "    0123456789012345678901234567890123456789    ";
        String trimmedPotName = "0123456789012345678901234567890123456789";
        Pot pot = Pot.builder(potName).build();
        assertEquals(pot.getName(), trimmedPotName);
    }

    @Test
    public void create_Pot_check_has_Uuid_OK() {
        String potName = "Pot 1";
        Pot pot = Pot.builder(potName).build();
        assertNotNull(pot.getUuid());
    }

    // TODO 
    // - Rename Pot
    // - Rename Pot : checks on name
    // - Add PS : OK
    // - Add PS : wrong Pot Uuid
    // - Add PS : name already exists
    // - Rename PS : OK
    // - Rename PS : checks on name
}
