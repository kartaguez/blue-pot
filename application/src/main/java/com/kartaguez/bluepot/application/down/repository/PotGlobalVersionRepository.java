package com.kartaguez.bluepot.application.down.repository;

import java.util.UUID;

import com.kartaguez.bluepot.domain.model.PotGlobalVersion;

public interface PotGlobalVersionRepository {

        public PotGlobalVersion loadPotGlobalVersionByPotUuid(UUID potUuid);
        
        public PotGlobalVersion save(PotGlobalVersion potGlobalVersion);
}
