package com.kartaguez.bluepot.application._to_delete.down.repository;

import java.util.UUID;

import com.kartaguez.bluepot.domain._to_delete.model.PotGlobalVersion;

public interface PotGlobalVersionRepository {

        public PotGlobalVersion loadPotGlobalVersionByPotUuid(UUID potUuid);
        
        public PotGlobalVersion save(PotGlobalVersion potGlobalVersion);
}
