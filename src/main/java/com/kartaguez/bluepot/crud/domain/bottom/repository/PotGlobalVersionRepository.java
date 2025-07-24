package com.kartaguez.bluepot.crud.domain.bottom.repository;

import java.util.UUID;

import com.kartaguez.bluepot.crud.domain.model.PotGlobalVersion;

public interface PotGlobalVersionRepository {

        public PotGlobalVersion loadPotGlobalVersionByPotUuid(UUID potUuid);
        
        public void save(PotGlobalVersion potGlobalVersion);
}
