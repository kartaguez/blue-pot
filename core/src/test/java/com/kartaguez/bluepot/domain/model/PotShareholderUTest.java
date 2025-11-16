package com.kartaguez.bluepot.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.kartaguez.bluepot.model.PotShareholder;

public class PotShareholderUTest {

    @Test
    public void create_PotShareholder_with_potUuid_and_name_OK() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = "Alex";
        PotShareholder potShareholder = PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName).build();
        assertNotNull(potShareholder);
        assertEquals(potShareholder.getName(), potShareholderName);
    }

    @Test
    public void create_PotShareholder_with_null_pot_uuid_KO() {
        UUID potUuid = null;
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = "Alex";
        assertThrows(IllegalArgumentException.class, () -> PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName));
    }

    @Test
    public void create_PotShareholder_with_null_uuid_KO() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = null;
        String potShareholderName = "Alex";
        assertThrows(IllegalArgumentException.class, () -> PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName));
    }

    @Test
    public void create_PotShareholder_with_null_name_KO() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = null;
        assertThrows(IllegalArgumentException.class, () -> PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName));
    }

    @Test
    public void create_PotShareholder_with_empty_name_KO() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = "";
        assertThrows(IllegalArgumentException.class, () -> PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName));
    }

    @Test
    public void create_PotShareholder_with_blank_name_KO() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = " ";
        assertThrows(IllegalArgumentException.class, () -> PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName));
    }

    @Test
    public void create_PotShareholder_with_trimmable_name_OK() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = " Alex ";
        String potShareholderTrimmedName = "Alex";
        PotShareholder potShareholder = PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName).build();
        assertEquals(potShareholder.getName(), potShareholderTrimmedName);
    }

    @Test
    public void create_PotShareholder_with_name_too_long_KO() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = "01234567890123456789012345678901234567890";
        assertThrows(IllegalArgumentException.class, () -> PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName));
    }

    @Test
    public void create_PotShareholder_with_name_max_length_OK() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = "0123456789012345678901234567890123456789";
        PotShareholder potShareholder = PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName).build();
        assertEquals(potShareholder.getName(), potShareholderName);
    }

    @Test
    public void create_PotShareholder_with_trimmed_name_max_length_OK() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = "    0123456789012345678901234567890123456789    ";
        String trimmedPotShareholderName = "0123456789012345678901234567890123456789";
        PotShareholder potShareholder = PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName).build();
        assertEquals(potShareholder.getName(), trimmedPotShareholderName);
    }   

    @Test
    public void create_PotShareholder_check_has_Uuid_OK() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = "0123456789012345678901234567890123456789";
        PotShareholder potShareholder = PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName).build();
        assertNotNull(potShareholder.getUuid());
    }

    @Test
    public void rename_PotShareholder_OK() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = "Alex";
        PotShareholder potShareholder = PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName).build();
        String potShareholderNewName = "Arthur";
        potShareholder.rename(potShareholderNewName);
        assertEquals(potShareholder.getName(), potShareholderNewName);
    }

    @Test
    public void rename_PotShareholder_with_null_name_KO() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = "Alex";
        PotShareholder potShareholder = PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName).build();
        String potShareholderNewName = null;
        assertThrows(IllegalArgumentException.class, () -> potShareholder.rename(potShareholderNewName));
    }

    @Test
    public void rename_PotShareholder_with_empty_name_KO() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = "Alex";
        PotShareholder potShareholder = PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName).build();
        String potShareholderNewName = "";
        assertThrows(IllegalArgumentException.class, () -> potShareholder.rename(potShareholderNewName));
    }

    @Test
    public void rename_PotShareholder_with_blank_name_KO() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = "Alex";
        PotShareholder potShareholder = PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName).build();
        String potShareholderNewName = " ";
        assertThrows(IllegalArgumentException.class, () -> potShareholder.rename(potShareholderNewName));
    }

    @Test
    public void rename_PotShareholder_with_trimmable_name_OK() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = "Alex";
        PotShareholder potShareholder = PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName).build();
        String potShareholderNewName = " Arthur ";
        potShareholder.rename(potShareholderNewName);
        String potTrimmedNewName = "Arthur";
        assertEquals(potShareholder.getName(), potTrimmedNewName);
    }

    @Test
    public void rename_PotShareholder_with_name_too_long_KO() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = "Alex";
        PotShareholder potShareholder = PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName).build();
        String potShareholderNewName = "01234567890123456789012345678901234567890";
        assertThrows(IllegalArgumentException.class, () -> potShareholder.rename(potShareholderNewName));
    }

    @Test
    public void rename_PotShareholder_with_name_max_length_OK() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = "Alex";
        PotShareholder potShareholder = PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName).build();
        String potShareholderNewName = "0123456789012345678901234567890123456789";
        potShareholder.rename(potShareholderNewName);
        assertEquals(potShareholder.getName(), potShareholderNewName);
    }

    @Test
    public void rename_PotShareholder_with_trimmed_name_max_length_OK() {
        UUID potUuid = UUID.randomUUID();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = "Alex";
        PotShareholder potShareholder = PotShareholder.builder(potShareholderUuid, potUuid, potShareholderName).build();
        String potShareholderNewName = "    0123456789012345678901234567890123456789    ";
        String trimmedPotName = "0123456789012345678901234567890123456789";
        potShareholder.rename(potShareholderNewName);
        assertEquals(potShareholder.getName(), trimmedPotName);
    }
}
