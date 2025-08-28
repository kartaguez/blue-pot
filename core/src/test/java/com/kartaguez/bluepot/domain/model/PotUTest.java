package com.kartaguez.bluepot.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;

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

    @Test
    public void rename_Pot_OK() {
        String potName = "Pot 1";
        Pot pot = Pot.builder(potName).build();
        String potNewName = "Pot 2";
        pot.rename(potNewName);
        assertEquals(pot.getName(), potNewName);
    }

    @Test
    public void rename_Pot_with_null_name_KO() {
        String potName = "Pot 1";
        Pot pot = Pot.builder(potName).build();
        String potNewName = null;
        assertThrows(IllegalArgumentException.class, () -> pot.rename(potNewName));
    }

    @Test
    public void rename_Pot_with_empty_name_KO() {
        String potName = "Pot 1";
        Pot pot = Pot.builder(potName).build();
        String potNewName = "";
        assertThrows(IllegalArgumentException.class, () -> pot.rename(potNewName));
    }

    @Test
    public void rename_Pot_with_blank_name_KO() {
        String potName = "Pot 1";
        Pot pot = Pot.builder(potName).build();
        String potNewName = " ";
        assertThrows(IllegalArgumentException.class, () -> pot.rename(potNewName));
    }

    @Test
    public void rename_Pot_with_trimmable_name_OK() {
        String potName = "Pot 1";
        Pot pot = Pot.builder(potName).build();
        String potNewName = " Pot 2 ";
        pot.rename(potNewName);
        String potTrimmedNewName = "Pot 2";
        assertEquals(pot.getName(), potTrimmedNewName);
    }

    @Test
    public void rename_Pot_with_name_too_long_KO() {
        String potName = "Pot 1";
        Pot pot = Pot.builder(potName).build();
        String potNewName = "01234567890123456789012345678901234567890";
        assertThrows(IllegalArgumentException.class, () -> pot.rename(potNewName));
    }

    @Test
    public void rename_Pot_with_name_max_length_OK() {
        String potName = "Pot 1";
        Pot pot = Pot.builder(potName).build();
        String potNewName = "0123456789012345678901234567890123456789";
        pot.rename(potNewName);
        assertEquals(pot.getName(), potNewName);
    }

    @Test
    public void rename_Pot_with_trimmed_name_max_length_OK() {
        String potName = "Pot 1";
        Pot pot = Pot.builder(potName).build();
        String potNewName = "    0123456789012345678901234567890123456789    ";
        String trimmedPotName = "0123456789012345678901234567890123456789";
        pot.rename(potNewName);
        assertEquals(pot.getName(), trimmedPotName);
    }

    @Test
    public void create_Pot_add_PotShareholder_OK() {
        String potName = "Pot 1";
        Pot pot = Pot.builder(potName).build();
        String potShareholderName = "Alex";
        pot.addPotShareholder(potShareholderName);
    }

    @Test
    public void create_Pot_add_2_PotShareholders_OK() {
        String potName = "Pot 1";
        Pot pot = Pot.builder(potName).build();
        ArrayList<String> potShareholderNames = new ArrayList<String>();
        potShareholderNames.add("Alex");
        potShareholderNames.add("Bob");
        pot.addPotShareholders(potShareholderNames);
    }

    @Test
    public void create_Pot_add_PotShareholders_null_list_KO() {
        String potName = "Pot 1";
        Pot pot = Pot.builder(potName).build();
        ArrayList<String> potShareholderNames = null;
        assertThrows(IllegalArgumentException.class, () -> pot.addPotShareholders(potShareholderNames));
    }

    @Test
    public void create_Pot_add_PotShareholders_empty_list_OK() {
        String potName = "Pot 1";
        Pot pot = Pot.builder(potName).build();
        ArrayList<String> potShareholderNames = new ArrayList<String>();
        pot.addPotShareholders(potShareholderNames);
    }

    @Test
    public void create_Pot_add_PotShareholders_duplicate_in_list_KO() {
        String potName = "Pot 1";
        Pot pot = Pot.builder(potName).build();
        ArrayList<String> potShareholderNames = new ArrayList<String>();
        potShareholderNames.add("Alex");
        potShareholderNames.add("Alex");
        assertThrows(IllegalArgumentException.class, () -> pot.addPotShareholders(potShareholderNames));
    }

    @Test
    public void create_Pot_add_PotShareholders_name_already_in_pot_KO() {
        String potName = "Pot 1";
        Pot pot = Pot.builder(potName).build();
        pot.addPotShareholder("Alex");
        ArrayList<String> potShareholderNames = new ArrayList<String>();
        potShareholderNames.add("Alex");
        potShareholderNames.add("Bob");
        assertThrows(IllegalArgumentException.class, () -> pot.addPotShareholders(potShareholderNames));
    }

    // TODO 
    // - Rename PS : OK
    // - Rename PS : checks on name
}
