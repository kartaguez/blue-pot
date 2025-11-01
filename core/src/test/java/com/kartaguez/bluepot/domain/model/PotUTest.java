package com.kartaguez.bluepot.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.HashMap;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.kartaguez.bluepot.model.Pot;

public class PotUTest {

    @Test
    public void create_Pot_with_name() {
        String potName = "Pot 1";
        UUID potUuid = UUID.randomUUID();
        Pot pot = Pot.builder(potUuid, potName).build();
        assertNotNull(pot);
        assertEquals(pot.getName(), potName);
    }

    @Test
    public void create_Pot_with_uuis_null_KO() {
        UUID potUuid = null;
        String potName = "Pot 1";
        assertThrows(IllegalArgumentException.class, () -> Pot.builder(potUuid, potName));
    }


    @Test
    public void create_Pot_with_name_null_KO() {
        UUID potUuid = UUID.randomUUID();
        String potName = null;
        assertThrows(IllegalArgumentException.class, () -> Pot.builder(potUuid, potName));
    }

    @Test
    public void create_Pot_with_name_empty_KO() {
        UUID potUuid = UUID.randomUUID();
        String potName = "";
        assertThrows(IllegalArgumentException.class, () -> Pot.builder(potUuid, potName));
    }

    @Test
    public void create_Pot_with_name_blank_KO() {
        UUID potUuid = UUID.randomUUID();
        String potName = " ";
        assertThrows(IllegalArgumentException.class, () -> Pot.builder(potUuid, potName));
    }

    @Test
    public void create_Pot_with_trimmable_name_OK() {
        UUID potUuid = UUID.randomUUID();
        String potName = " Pot 1 ";
        String trimmedPotName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        assertEquals(pot.getName(), trimmedPotName);
    }

    @Test
    public void create_Pot_with_name_too_long_KO() {
        UUID potUuid = UUID.randomUUID();
        String potName = "01234567890123456789012345678901234567890";
        assertThrows(IllegalArgumentException.class, () -> Pot.builder(potUuid, potName));
    }

    @Test
    public void create_Pot_with_name_max_length_OK() {
        UUID potUuid = UUID.randomUUID();
        String potName = "0123456789012345678901234567890123456789";
        Pot pot = Pot.builder(potUuid, potName).build();
        assertEquals(pot.getName(), potName);
    }

    @Test
    public void create_Pot_with_trimmed_name_max_length_OK() {
        UUID potUuid = UUID.randomUUID();
        String potName = "    0123456789012345678901234567890123456789    ";
        String trimmedPotName = "0123456789012345678901234567890123456789";
        Pot pot = Pot.builder(potUuid, potName).build();
        assertEquals(pot.getName(), trimmedPotName);
    }

    @Test
    public void create_Pot_check_has_Uuid_OK() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        assertNotNull(pot.getUuid());
    }

    @Test
    public void rename_Pot_OK() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        String potNewName = "Pot 2";
        pot.rename(potNewName);
        assertEquals(pot.getName(), potNewName);
    }

    @Test
    public void rename_Pot_with_null_name_KO() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        String potNewName = null;
        assertThrows(IllegalArgumentException.class, () -> pot.rename(potNewName));
    }

    @Test
    public void rename_Pot_with_empty_name_KO() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        String potNewName = "";
        assertThrows(IllegalArgumentException.class, () -> pot.rename(potNewName));
    }

    @Test
    public void rename_Pot_with_blank_name_KO() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        String potNewName = " ";
        assertThrows(IllegalArgumentException.class, () -> pot.rename(potNewName));
    }

    @Test
    public void rename_Pot_with_trimmable_name_OK() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        String potNewName = " Pot 2 ";
        pot.rename(potNewName);
        String potTrimmedNewName = "Pot 2";
        assertEquals(pot.getName(), potTrimmedNewName);
    }

    @Test
    public void rename_Pot_with_name_too_long_KO() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        String potNewName = "01234567890123456789012345678901234567890";
        assertThrows(IllegalArgumentException.class, () -> pot.rename(potNewName));
    }

    @Test
    public void rename_Pot_with_name_max_length_OK() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        String potNewName = "0123456789012345678901234567890123456789";
        pot.rename(potNewName);
        assertEquals(pot.getName(), potNewName);
    }

    @Test
    public void rename_Pot_with_trimmed_name_max_length_OK() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        String potNewName = "    0123456789012345678901234567890123456789    ";
        String trimmedPotName = "0123456789012345678901234567890123456789";
        pot.rename(potNewName);
        assertEquals(pot.getName(), trimmedPotName);
    }

    @Test
    public void create_Pot_add_PotShareholder_OK() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = "Alex";
        pot.addPotShareholder(potShareholderUuid, potShareholderName);
    }

    @Test
    public void create_Pot_add_2_PotShareholders_OK() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        HashMap<UUID, String> potShareholderNames = new HashMap<UUID, String>();
        potShareholderNames.put(UUID.randomUUID(), "Alex");
        potShareholderNames.put(UUID.randomUUID(), "Bob");
        pot.addPotShareholders(potShareholderNames);
    }

    @Test
    public void create_Pot_add_PotShareholders_null_list_KO() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        HashMap<UUID, String> potShareholderNames = null;
        assertThrows(IllegalArgumentException.class, () -> pot.addPotShareholders(potShareholderNames));
    }

    @Test
    public void create_Pot_add_PotShareholders_empty_list_OK() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        HashMap<UUID, String> potShareholderNames = new HashMap<UUID, String>();
        pot.addPotShareholders(potShareholderNames);
    }

    @Test
    public void create_Pot_add_PotShareholders_Uuid_already_in_pot_KO() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        UUID potShareholderUuid = UUID.randomUUID();
        String potShareholderName = "Alex";
        pot.addPotShareholder(potShareholderUuid, potShareholderName);
        HashMap<UUID, String> potShareholderNames = new HashMap<UUID, String>();
        potShareholderNames.put(potShareholderUuid, "Bob");
        potShareholderNames.put(UUID.randomUUID(), "Chris");
        assertThrows(IllegalArgumentException.class, () -> pot.addPotShareholders(potShareholderNames));
    }

    @Test
    public void create_Pot_add_PotShareholders_name_already_in_pot_KO() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        pot.addPotShareholder(UUID.randomUUID(), "Alex");
        HashMap<UUID, String> potShareholderNames = new HashMap<UUID, String>();
        potShareholderNames.put(UUID.randomUUID(), "Alex");
        potShareholderNames.put(UUID.randomUUID(), "Bob");
        assertThrows(IllegalArgumentException.class, () -> pot.addPotShareholders(potShareholderNames));
    }

    @Test
    public void create_Pot_add_PotShareholders_rename_PotShareholder_OK() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        pot.addPotShareholder(UUID.randomUUID(), "Alex");
        HashMap<UUID, String> potShareholderNames = new HashMap<UUID, String>();
        potShareholderNames.put(UUID.randomUUID(), "Alex");
        potShareholderNames.put(UUID.randomUUID(), "Bob");
        pot.addPotShareholders(potShareholderNames);

        UUID potShareholderUUid = pot.getPotShareholders().values().iterator().next().getUuid();

        pot.renamePotShareholder(potShareholderUUid, "Arthur");
        assertEquals(pot.getPotShareholders().get(potShareholderUUid).getName(), "Arthur");
    }

    @Test
    public void create_Pot_add_PotShareholders_rename_null_Uuid_KO() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        pot.addPotShareholder(UUID.randomUUID(), "Alex");
        HashMap<UUID, String> potShareholderNames = new HashMap<UUID, String>();
        potShareholderNames.put(UUID.randomUUID(), "Alex");
        potShareholderNames.put(UUID.randomUUID(), "Bob");
        pot.addPotShareholders(potShareholderNames);

        UUID potShareholderUUid = null;

        assertThrows(IllegalArgumentException.class, () -> pot.renamePotShareholder(potShareholderUUid, "Arthur"));
    }

    @Test
    public void create_Pot_add_PotShareholders_rename_non_existing_Uuid_KO() {
        UUID potUuid = UUID.randomUUID();
        String potName = "Pot 1";
        Pot pot = Pot.builder(potUuid, potName).build();
        pot.addPotShareholder(UUID.randomUUID(), "Alex");
        HashMap<UUID, String> potShareholderNames = new HashMap<UUID, String>();
        potShareholderNames.put(UUID.randomUUID(), "Alex");
        potShareholderNames.put(UUID.randomUUID(), "Bob");
        pot.addPotShareholders(potShareholderNames);

        UUID potShareholderUUid = UUID.randomUUID();

        assertThrows(IllegalArgumentException.class, () -> pot.renamePotShareholder(potShareholderUUid, "Arthur"));
    }

    // @Test
    // public void create_Pot_add_PotShareholders_rename_PotShareholder_existing_name_KO() {
    //     UUID potUuid = UUID.randomUUID();
    //     String potName = "Pot 1";
    //     Pot pot = Pot.builder(potUuid, potName).build();
    //     pot.addPotShareholder(UUID.randomUUID(), "Alex");
    //     HashMap<UUID, String> potShareholderNames = new HashMap<UUID, String>();
    //     potShareholderNames.put(UUID.randomUUID(), "Alex");
    //     potShareholderNames.put(UUID.randomUUID(), "Bob");
    //     pot.addPotShareholders(potShareholderNames);

    //     Iterator<PotShareholder> potShareholders = pot.getPotShareholders().values().iterator();
    //     PotShareholder potShareholder = null;
    //     boolean otherPSFound = false;
    //     while (!otherPSFound) {
    //         potShareholder = potShareholders.next();
    //         otherPSFound = (!"Bob".equals(potShareholder.getName()));
    //     }
    //     UUID potShareholderUUid = potShareholder.getUuid();

    //     assertThrows(IllegalArgumentException.class, () -> pot.renamePotShareholder(potShareholderUUid, "Bob"));
    // }

}
