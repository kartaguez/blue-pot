package com.kartaguez.bluepot.domain.model.object.superclass;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.kartaguez.bluepot.domain.model.PotGlobalVersion;
import com.kartaguez.bluepot.utils.Constants;

public class VersionedObjectUTest {


    @Test
    void check_PotGlobalVersion_Increment_Version() {
        UUID potUuid = UUID.randomUUID();
        PotGlobalVersion potGlobalVersion = PotGlobalVersion.forNewPot(potUuid);
        assertEquals(potGlobalVersion.getPotVersion(), Constants.FIRST_VERSION);
        potGlobalVersion.incrementVersion();
        assertEquals(potGlobalVersion.getPotVersion(), Constants.FIRST_VERSION + 1);
    }

}
