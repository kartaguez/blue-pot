package com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.mapper;

import com.kartaguez.bluepot.crud.domain.model.object.PotShareholder;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.object.PotShareholderEntity;
import com.kartaguez.bluepot.crud.utils.Constants;

import lombok.NonNull;

public class PotShareholderEntityMapper {

    public PotShareholder toDomain(@NonNull PotShareholderEntity potShareholderEntity, long targetGlobalVersion) {
        long hCreatedAtVersion = Constants.NULL_VERSION;
        if (null != potShareholderEntity.getCreatedAtVersion()) {
            hCreatedAtVersion = potShareholderEntity.getCreatedAtVersion().longValue();
        }
        long hDeletedAtVersion = Constants.NULL_VERSION;
        if (null != potShareholderEntity.getDeletedAtVersion()) {
            hDeletedAtVersion = potShareholderEntity.getDeletedAtVersion().longValue();
        }
        return PotShareholder.hydrateRoot(potShareholderEntity.getUuid(), potShareholderEntity.getPotUuid(), targetGlobalVersion, hCreatedAtVersion, hDeletedAtVersion, potShareholderEntity.getName());
    }

    
    public PotShareholderEntity toEntity(@NonNull PotShareholder potShareholder) {
        return new PotShareholderEntity(potShareholder.getUuid(), potShareholder.getPotUuid(), potShareholder.getCreatedAtVersion(), potShareholder.getDeletedAtVersion(), potShareholder.getName());
    }

}
