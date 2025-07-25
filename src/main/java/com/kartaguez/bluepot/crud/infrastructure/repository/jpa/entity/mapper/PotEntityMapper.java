package com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.mapper;

import com.kartaguez.bluepot.crud.domain.model.object.Pot;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.PotEntity;
import com.kartaguez.bluepot.crud.utils.Constants;

import lombok.NonNull;

public class PotEntityMapper {

    public Pot toDomain(@NonNull PotEntity potEntity, long _targetGlobalVersion) {
        long hCreatedAtVersion = Constants.NULL_VERSION;
        if (null != potEntity.getCreatedAtVersion()) {
            hCreatedAtVersion = potEntity.getCreatedAtVersion().longValue();
        }
        long hDeletedAtVersion = Constants.NULL_VERSION;
        if (null != potEntity.getDeletedAtVersion()) {
            hDeletedAtVersion = potEntity.getDeletedAtVersion().longValue();
        }
        return Pot.hydrateRoot(potEntity.getUuid(), potEntity.getVersion(), _targetGlobalVersion, hCreatedAtVersion, hDeletedAtVersion, potEntity.getName());
    }

    
    public PotEntity toEntity(@NonNull Pot pot) {
        return new PotEntity(pot.getUuid(), pot.getVersion(), pot.getCreatedAtVersion(), pot.getDeletedAtVersion(), pot.getName());
    }

    public PotEntity updateEntity(@NonNull PotEntity potEntity, @NonNull Pot pot) {
        if (potEntity.getUuid() == null || pot.getUuid() == null) {
            throw new IllegalArgumentException("UUID cannot be null.");
        }
        if (!potEntity.getUuid().equals(pot.getUuid())) {
            throw new IllegalArgumentException("UUID mismatch.");
        }
        potEntity.setVersion(pot.getVersion());
        potEntity.setCreatedAtVersion(pot.getCreatedAtVersion());
        potEntity.setDeletedAtVersion(pot.getDeletedAtVersion());
        potEntity.setName(pot.getName());

        return potEntity;
    }
}
