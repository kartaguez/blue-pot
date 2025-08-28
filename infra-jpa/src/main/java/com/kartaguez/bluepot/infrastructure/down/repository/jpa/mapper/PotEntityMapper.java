package com.kartaguez.bluepot.infrastructure.down.repository.jpa.mapper;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.domain.model.Pot_old1;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity.PotEntity;
import com.kartaguez.bluepot.utils.Constants;

import lombok.NonNull;

@Component
public class PotEntityMapper {

    public Pot_old1 toDomain(@NonNull PotEntity potEntity, long targetGlobalVersion) {
        long hCreatedAtVersion = Constants.NULL_VERSION;
        if (null != potEntity.getCreatedAtVersion()) {
            hCreatedAtVersion = potEntity.getCreatedAtVersion().longValue();
        }
        long hDeletedAtVersion = Constants.NULL_VERSION;
        if (null != potEntity.getDeletedAtVersion()) {
            hDeletedAtVersion = potEntity.getDeletedAtVersion().longValue();
        }
        return Pot_old1.hydrateRoot(potEntity.getUuid(), targetGlobalVersion, hCreatedAtVersion, hDeletedAtVersion, potEntity.getName());
    }

    
    public PotEntity toEntity(@NonNull Pot_old1 pot) {
        return new PotEntity(pot.getUuid(), pot.getCreatedAtVersion(), pot.getDeletedAtVersion(), pot.getName());
    }

}
