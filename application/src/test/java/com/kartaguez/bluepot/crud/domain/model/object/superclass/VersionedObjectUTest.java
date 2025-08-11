package com.kartaguez.bluepot.crud.domain.model.object.superclass;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.kartaguez.bluepot.crud.domain.model.PotGlobalVersion;
import com.kartaguez.bluepot.crud.utils.Constants;

public class VersionedObjectUTest {

    @Test
    void check_PotGlobalVersion_State_After_Creation() {
        UUID potUuid = UUID.randomUUID();
        PotGlobalVersion potGlobalVersion = PotGlobalVersion.create(potUuid);
        assertEquals(potGlobalVersion.getPotVersion(), Constants.FIRST_VERSION);
        assertEquals(potGlobalVersion.getPotUuid(), potUuid);
    }

    @Test
    void check_PotGlobalVersion_Increment_Version() {
        UUID potUuid = UUID.randomUUID();
        PotGlobalVersion potGlobalVersion = PotGlobalVersion.create(potUuid);
        assertEquals(potGlobalVersion.getPotVersion(), Constants.FIRST_VERSION);
        potGlobalVersion.incrementVersion();
        assertEquals(potGlobalVersion.getPotVersion(), Constants.FIRST_VERSION + 1);
    }

}
