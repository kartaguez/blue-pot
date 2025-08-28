package com.kartaguez.bluepot.infrastructure.down.repository.jpa.mapper;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.domain.model.PotShareholder_old1;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity.PotShareholderEntity;
import com.kartaguez.bluepot.utils.Constants;

import lombok.NonNull;

@Component
public class PotShareholderEntityMapper {

    public PotShareholder_old1 toDomain(@NonNull PotShareholderEntity potShareholderEntity, long targetGlobalVersion) {
        long hCreatedAtVersion = Constants.NULL_VERSION;
        if (null != potShareholderEntity.getCreatedAtVersion()) {
            hCreatedAtVersion = potShareholderEntity.getCreatedAtVersion().longValue();
        }
        long hDeletedAtVersion = Constants.NULL_VERSION;
        if (null != potShareholderEntity.getDeletedAtVersion()) {
            hDeletedAtVersion = potShareholderEntity.getDeletedAtVersion().longValue();
        }
        return PotShareholder_old1.hydrateRoot(potShareholderEntity.getUuid(), potShareholderEntity.getPotUuid(), targetGlobalVersion, hCreatedAtVersion, hDeletedAtVersion, potShareholderEntity.getName());
    }

    
    public PotShareholderEntity toEntity(@NonNull PotShareholder_old1 potShareholder) {
        return new PotShareholderEntity(potShareholder.getUuid(), potShareholder.getPotUuid(), potShareholder.getCreatedAtVersion(), potShareholder.getDeletedAtVersion(), potShareholder.getName());
    }

}
