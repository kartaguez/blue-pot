package com.kartaguez.bluepot.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

public class PotShareholderUTest {

    @Test
    public void create_PotShareholder_with_potUuid_and_name_OK() {
        String potShareholderName = "Alex";
        UUID potUuid = UUID.randomUUID();
        PotShareholder potShareholder = PotShareholder.builder(potUuid, potShareholderName).build();
        assertNotNull(potShareholder);
        assertEquals(potShareholder.getPotUuid(), potUuid);
        assertEquals(potShareholder.getName(), potShareholderName);
    }

    @Test
    public void create_PotShareholder_with_null_potUuid_KO() {
        String potShareholderName = "Alex";
        UUID potUuid = null;
        assertThrows(IllegalArgumentException.class, () -> PotShareholder.builder(potUuid, potShareholderName));
    }

    @Test
    public void create_PotShareholder_with_null_name_KO() {
        String potShareholderName = null;
        UUID potUuid = UUID.randomUUID();
        assertThrows(IllegalArgumentException.class, () -> PotShareholder.builder(potUuid, potShareholderName));
    }

    @Test
    public void create_PotShareholder_with_empty_name_KO() {
        String potShareholderName = "";
        UUID potUuid = UUID.randomUUID();
        assertThrows(IllegalArgumentException.class, () -> PotShareholder.builder(potUuid, potShareholderName));
    }

    @Test
    public void create_PotShareholder_with_blank_name_KO() {
        String potShareholderName = " ";
        UUID potUuid = UUID.randomUUID();
        assertThrows(IllegalArgumentException.class, () -> PotShareholder.builder(potUuid, potShareholderName));
    }

    @Test
    public void create_PotShareholder_with_trimmable_name_OK() {
        String potShareholderName = " Alex ";
        String potShareholderTrimmedName = "Alex";
        UUID potUuid = UUID.randomUUID();
        PotShareholder potShareholder = PotShareholder.builder(potUuid, potShareholderName).build();
        assertEquals(potShareholder.getName(), potShareholderTrimmedName);
    }

    @Test
    public void create_PotShareholder_with_name_too_long_KO() {
        String potShareholderName = "01234567890123456789012345678901234567890";
        UUID potUuid = UUID.randomUUID();
        assertThrows(IllegalArgumentException.class, () -> PotShareholder.builder(potUuid, potShareholderName));
    }

    @Test
    public void create_PotShareholder_with_name_max_length_OK() {
        String potShareholderName = "0123456789012345678901234567890123456789";
        UUID potUuid = UUID.randomUUID();
        PotShareholder potShareholder = PotShareholder.builder(potUuid, potShareholderName).build();
        assertEquals(potShareholder.getName(), potShareholderName);
    }

    @Test
    public void create_PotShareholder_with_trimmed_name_max_length_OK() {
        String potShareholderName = "    0123456789012345678901234567890123456789    ";
        String trimmedPotShareholderName = "0123456789012345678901234567890123456789";
        UUID potUuid = UUID.randomUUID();
        PotShareholder potShareholder = PotShareholder.builder(potUuid, potShareholderName).build();
        assertEquals(potShareholder.getName(), trimmedPotShareholderName);
    }

    @Test
    public void create_PotShareholder_check_has_Uuid_OK() {
        String potShareholderName = "0123456789012345678901234567890123456789";
        UUID potUuid = UUID.randomUUID();
        PotShareholder potShareholder = PotShareholder.builder(potUuid, potShareholderName).build();
        assertNotNull(potShareholder.getUuid());
    }
}
