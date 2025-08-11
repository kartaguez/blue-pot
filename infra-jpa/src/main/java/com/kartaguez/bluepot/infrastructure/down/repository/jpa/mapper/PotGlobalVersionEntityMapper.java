package com.kartaguez.bluepot.infrastructure.down.repository.jpa.mapper;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.domain.model.PotGlobalVersion;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity.PotGlobalVersionEntity;

import lombok.NonNull;

@Component
public class PotGlobalVersionEntityMapper {

    public PotGlobalVersion toDomain(@NonNull PotGlobalVersionEntity potGlobalVersionEntity) {
        return PotGlobalVersion.hydrateRoot(potGlobalVersionEntity.getId(), potGlobalVersionEntity.getPotUuid(), potGlobalVersionEntity.getPotVersion());
    }

    public PotGlobalVersionEntity toEntity(@NonNull PotGlobalVersion potGlobalVersion) {
        return new PotGlobalVersionEntity(potGlobalVersion.getId(), potGlobalVersion.getPotUuid(), potGlobalVersion.getPotVersion());
    }

}
