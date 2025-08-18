package com.kartaguez.bluepot.domain.model.object;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.kartaguez.bluepot.domain.model.Pot2;
import com.kartaguez.bluepot.domain.model.PotGlobalVersion;
import com.kartaguez.bluepot.domain.model.PotShareholder2;
import com.kartaguez.bluepot.utils.Constants;

public class Pot2UTest {

    @Test
    void check_PotGlobalVersion_State_After_Creation() {
        UUID potUuid = UUID.randomUUID();
        PotGlobalVersion potGlobalVersion = PotGlobalVersion.forNewPot(potUuid);
        assertEquals(potGlobalVersion.getTargetPotVersion(), Constants.FIRST_VERSION);
        assertEquals(potGlobalVersion.getPotUuid(), potUuid);
    }

    @Test
    void create_Pot2_add_PotShareholders_add_Expense_update_Shareholders() {

        Pot2 pot1 = Pot2.create("Pot 1");
        assertNull(pot1.getBaseVersion());
        assertEquals(pot1.getPotGlobalVersion().getCurrentPotVersion(), Constants.BEFORE_FIRST_VERSION);
        assertEquals(pot1.getPotGlobalVersion().getTargetPotVersion(), Constants.FIRST_VERSION);
        assertEquals(pot1.isDeleted(), false);
        assertEquals(pot1.getName(), "Pot 1");

        pot1.rename("Pot 11");
        assertNull(pot1.getBaseVersion());
        assertEquals(pot1.getPotGlobalVersion().getCurrentPotVersion(), Constants.BEFORE_FIRST_VERSION);
        assertEquals(pot1.getPotGlobalVersion().getTargetPotVersion(), Constants.FIRST_VERSION);
        assertEquals(pot1.isDeleted(), false);
        assertEquals(pot1.getName(), "Pot 11");

        pot1.addPotShareholder("Alex");
        assertNull(pot1.getBaseVersion());
        assertEquals(pot1.getPotGlobalVersion().getCurrentPotVersion(), Constants.BEFORE_FIRST_VERSION);
        assertEquals(pot1.getPotGlobalVersion().getTargetPotVersion(), Constants.FIRST_VERSION);
        assertEquals(pot1.isDeleted(), false);
        assertEquals(pot1.getName(), "Pot 11");
        assertEquals(pot1.getPotShareholders().size(), 1);
        PotShareholder2 potShareholder1 = pot1.getPotShareholders().values().iterator().next();
        assertNull(potShareholder1.getBaseVersion());
        assertEquals(potShareholder1.getPotGlobalVersion().getCurrentPotVersion(), Constants.BEFORE_FIRST_VERSION);
        assertEquals(potShareholder1.getPotGlobalVersion().getTargetPotVersion(), Constants.FIRST_VERSION);

        pot1.addPotShareholder("Barbara");
        assertNull(pot1.getBaseVersion());
        assertEquals(pot1.getPotGlobalVersion().getCurrentPotVersion(), Constants.BEFORE_FIRST_VERSION);
        assertEquals(pot1.getPotGlobalVersion().getTargetPotVersion(), Constants.FIRST_VERSION);
        assertEquals(pot1.isDeleted(), false);
        assertEquals(pot1.getName(), "Pot 11");
        assertEquals(pot1.getPotShareholders().size(), 2);
        pot1.getPotShareholders().values().iterator().next();
        PotShareholder2 potShareholder2 = pot1.getPotShareholders().values().iterator().next();
        assertNull(potShareholder2.getBaseVersion());
        assertEquals(potShareholder2.getPotGlobalVersion().getCurrentPotVersion(), Constants.BEFORE_FIRST_VERSION);
        assertEquals(potShareholder2.getPotGlobalVersion().getTargetPotVersion(), Constants.FIRST_VERSION);

        pot1.getPotGlobalVersion().markTargetVersionAsPersisted();
        pot1.markTargetVersionAsPersisted();
        assertNotNull(pot1.getBaseVersion());
        assertEquals(pot1.getBaseVersion(), pot1.getTargetVersion());
        assertEquals(pot1.getPotGlobalVersion().getCurrentPotVersion(), Constants.FIRST_VERSION);
        assertEquals(pot1.getPotGlobalVersion().getTargetPotVersion(), Constants.FIRST_VERSION + 1);
        assertEquals(pot1.isDeleted(), false);
        assertEquals(pot1.getPotShareholders().size(), 2);
    
        assertNotNull(potShareholder1.getBaseVersion());
        assertEquals(potShareholder1.getBaseVersion(), potShareholder1.getTargetVersion());
        assertEquals(potShareholder1.getPotGlobalVersion().getCurrentPotVersion(), Constants.FIRST_VERSION);
        assertEquals(potShareholder1.getPotGlobalVersion().getTargetPotVersion(), Constants.FIRST_VERSION + 1);

        assertNotNull(potShareholder2.getBaseVersion());
        assertEquals(potShareholder2.getBaseVersion(), potShareholder2.getTargetVersion());
        assertEquals(potShareholder2.getPotGlobalVersion().getCurrentPotVersion(), Constants.FIRST_VERSION);
        assertEquals(potShareholder2.getPotGlobalVersion().getTargetPotVersion(), Constants.FIRST_VERSION + 1);

    }

}
