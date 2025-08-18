package com.kartaguez.bluepot.domain.model.object;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Iterator;
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

        // Create pot "Pot 1"
        Pot2 pot1 = Pot2.create("Pot 1");
        assertNull(pot1.getBaseVersion());
        assertEquals(pot1.getPotGlobalVersion().getCurrentPotVersion(), Constants.BEFORE_FIRST_VERSION);
        assertEquals(pot1.getPotGlobalVersion().getTargetPotVersion(), Constants.FIRST_VERSION);
        assertEquals(pot1.isDeleted(), false);
        assertEquals(pot1.getName(), "Pot 1");

        // Rename "Pot 1" to "Pot 11"
        pot1.rename("Pot 11");
        assertNull(pot1.getBaseVersion());
        assertEquals(pot1.getPotGlobalVersion().getCurrentPotVersion(), Constants.BEFORE_FIRST_VERSION);
        assertEquals(pot1.getPotGlobalVersion().getTargetPotVersion(), Constants.FIRST_VERSION);
        assertEquals(pot1.isDeleted(), false);
        assertEquals(pot1.getName(), "Pot 11");

        // Add PotShareholder "Alex"
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
        assertEquals(potShareholder1.getPotGlobalVersion().getTargetPotVersion(), Constants.BEFORE_FIRST_VERSION + 1);

        // Add PotShareholder "Barbara"
        pot1.addPotShareholder("Barbara");
        assertNull(pot1.getBaseVersion());
        assertEquals(pot1.getPotGlobalVersion().getCurrentPotVersion(), Constants.BEFORE_FIRST_VERSION);
        assertEquals(pot1.getPotGlobalVersion().getTargetPotVersion(), Constants.BEFORE_FIRST_VERSION + 1);
        assertEquals(pot1.isDeleted(), false);
        assertEquals(pot1.getName(), "Pot 11");
        assertEquals(pot1.getPotShareholders().size(), 2);
        Iterator<PotShareholder2> potShareholders = pot1.getPotShareholders().values().iterator();
        potShareholders.next();
        PotShareholder2 potShareholder2 = potShareholders.next();
        assertNull(potShareholder2.getBaseVersion());
        assertEquals(potShareholder2.getPotGlobalVersion().getCurrentPotVersion(), Constants.BEFORE_FIRST_VERSION);
        assertEquals(potShareholder2.getPotGlobalVersion().getTargetPotVersion(), Constants.BEFORE_FIRST_VERSION + 1);

        // record target version
        pot1.recordTargetVersionPersisted();
        assertNotNull(pot1.getBaseVersion());
        assertEquals(pot1.getBaseVersion(), pot1.getTargetVersion());
        assertEquals(pot1.getPotGlobalVersion().getCurrentPotVersion(), Constants.BEFORE_FIRST_VERSION + 1);
        assertEquals(pot1.getPotGlobalVersion().getTargetPotVersion(), Constants.BEFORE_FIRST_VERSION + 2);
        assertEquals(pot1.isDeleted(), false);
        assertEquals(pot1.getPotShareholders().size(), 2);
    
        assertNotNull(potShareholder1.getBaseVersion());
        assertEquals(potShareholder1.getBaseVersion(), potShareholder1.getTargetVersion());
        assertEquals(potShareholder1.getPotGlobalVersion().getCurrentPotVersion(), Constants.BEFORE_FIRST_VERSION + 1);
        assertEquals(potShareholder1.getPotGlobalVersion().getTargetPotVersion(), Constants.BEFORE_FIRST_VERSION + 2);
        assertEquals(potShareholder1.getBaseVersion().activeFromVersion(), Constants.BEFORE_FIRST_VERSION + 1);
        assertEquals(potShareholder1.getBaseVersion().obsoleteFromVersion(), Constants.NULL_VERSION);
        assertEquals(potShareholder1.getBaseVersion().deleted(), false);
        assertEquals(potShareholder1.getTargetVersion().activeFromVersion(), Constants.BEFORE_FIRST_VERSION + 1);
        assertEquals(potShareholder1.getTargetVersion().obsoleteFromVersion(), Constants.NULL_VERSION);
        assertEquals(potShareholder1.getTargetVersion().deleted(), false);

        assertNotNull(potShareholder2.getBaseVersion());
        assertEquals(potShareholder2.getBaseVersion(), potShareholder2.getTargetVersion());
        assertEquals(potShareholder2.getPotGlobalVersion().getCurrentPotVersion(), Constants.BEFORE_FIRST_VERSION + 1);
        assertEquals(potShareholder2.getPotGlobalVersion().getTargetPotVersion(), Constants.BEFORE_FIRST_VERSION + 2);

        // rename PotShareholder1
        pot1.updatePotShareholder(potShareholder1.getUuid(), "Albert");
        
        assertNotNull(pot1.getBaseVersion());
        assertEquals(pot1.getBaseVersion(), pot1.getTargetVersion());
        assertEquals(pot1.getPotGlobalVersion().getCurrentPotVersion(), Constants.BEFORE_FIRST_VERSION + 1);
        assertEquals(pot1.getPotGlobalVersion().getTargetPotVersion(), Constants.BEFORE_FIRST_VERSION + 2);
        assertEquals(pot1.isDeleted(), false);
        assertEquals(pot1.getPotShareholders().size(), 2);
    
        assertNotNull(potShareholder1.getBaseVersion());
        assertNotEquals(potShareholder1.getBaseVersion(), potShareholder1.getTargetVersion());
        assertEquals(potShareholder1.getPotGlobalVersion().getCurrentPotVersion(), Constants.BEFORE_FIRST_VERSION + 1);
        assertEquals(potShareholder1.getPotGlobalVersion().getTargetPotVersion(), Constants.BEFORE_FIRST_VERSION + 2);
        assertEquals(potShareholder1.getName(), "Albert");
        assertEquals(potShareholder1.getBaseVersion().activeFromVersion(), Constants.BEFORE_FIRST_VERSION + 1);
        assertEquals(potShareholder1.getBaseVersion().obsoleteFromVersion(), Constants.NULL_VERSION);
        assertEquals(potShareholder1.getBaseVersion().deleted(), false);
        assertEquals(potShareholder1.getTargetVersion().activeFromVersion(), Constants.BEFORE_FIRST_VERSION + 2);
        assertEquals(potShareholder1.getTargetVersion().obsoleteFromVersion(), Constants.NULL_VERSION);
        assertEquals(potShareholder1.getTargetVersion().deleted(), false);

        assertNotNull(potShareholder2.getBaseVersion());
        assertEquals(potShareholder2.getBaseVersion(), potShareholder2.getTargetVersion());
        assertEquals(potShareholder2.getPotGlobalVersion().getCurrentPotVersion(), Constants.BEFORE_FIRST_VERSION + 1);
        assertEquals(potShareholder2.getPotGlobalVersion().getTargetPotVersion(), Constants.BEFORE_FIRST_VERSION + 2);

    }

}
