package com.kartaguez.bluepot.domain.model.object;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.kartaguez.bluepot.domain.model.Pot;
import com.kartaguez.bluepot.domain.model.PotGlobalVersion;
import com.kartaguez.bluepot.domain.model.PotShareholder;
import com.kartaguez.bluepot.domain.model.mutation.PotMutationResultSet;
import com.kartaguez.bluepot.domain.model.mutation.PotShareholderMutationResultSet;
import com.kartaguez.bluepot.utils.Constants;

public class PotUTest {

    @Test
    void check_PotGlobalVersion_State_After_Creation() {
        UUID potUuid = UUID.randomUUID();
        PotGlobalVersion potGlobalVersion = PotGlobalVersion.forNewPot(potUuid);
        assertEquals(potGlobalVersion.getPotVersion(), Constants.FIRST_VERSION);
        assertEquals(potGlobalVersion.getPotUuid(), potUuid);
    }

    @Test
    void check_PotGlobalVersion_Increment_Version() {
        UUID potUuid = UUID.randomUUID();
        PotGlobalVersion potGlobalVersion = PotGlobalVersion.forNewPot(potUuid);
        assertEquals(potGlobalVersion.getPotVersion(), Constants.FIRST_VERSION);
        potGlobalVersion.incrementVersion();
        assertEquals(potGlobalVersion.getPotVersion(), Constants.FIRST_VERSION + 1);
    }

    @Test
    void create_Pot_add_PotShareholders_add_Expense_update_Shareholders() {
        Pot pot1 = Pot.hydrateRoot(UUID.randomUUID(), 1, 1, Constants.NULL_VERSION, "Pot 1");
        assertEquals(pot1.getName(), "Pot 1");
        assertEquals(pot1.getCurrentGlobalVersion(), Constants.FIRST_VERSION);
        assertEquals(pot1.getCreatedAtVersion(), Constants.FIRST_VERSION);
        assertEquals(pot1.getDeletedAtVersion(), Constants.NULL_VERSION);
    
        PotShareholderMutationResultSet psMRS = pot1.addPotShareholder("Alex");
        assertEquals(pot1.getName(), "Pot 1");
        assertEquals(pot1.getCurrentGlobalVersion(), Constants.FIRST_VERSION + 1);
        assertEquals(pot1.getCreatedAtVersion(), Constants.FIRST_VERSION);
        assertEquals(pot1.getDeletedAtVersion(), Constants.NULL_VERSION);

        assertNull(psMRS.getObsoletePotShareholderInstance());
        PotShareholder newPs = psMRS.getNewPotShareholderInstance();
        assertNotNull(newPs);
        assertEquals(newPs.getCurrentGlobalVersion(), 2);
        assertEquals(newPs.getCreatedAtVersion(), 2);
        assertEquals(newPs.getDeletedAtVersion(), Constants.NULL_VERSION);
        assertEquals(newPs.getName(), "Alex");
    
        PotMutationResultSet pMRS = pot1.updateRoot("Pot 1.1");
        Pot oldP = pMRS.getObsoletePotInstance();
        assertEquals(oldP.getName(), "Pot 1");
        assertEquals(oldP.getCurrentGlobalVersion(), 3);
        assertEquals(pot1.getCreatedAtVersion(), 1);
        assertEquals(pot1.getDeletedAtVersion(), 3);
        Pot newP = pMRS.getNewPotInstance();
        assertEquals(oldP.getName(), "Pot 1.1");
        assertEquals(oldP.getCurrentGlobalVersion(), 3);
        assertEquals(pot1.getCreatedAtVersion(), 3);
        assertEquals(pot1.getDeletedAtVersion(), -1);


    }

}
