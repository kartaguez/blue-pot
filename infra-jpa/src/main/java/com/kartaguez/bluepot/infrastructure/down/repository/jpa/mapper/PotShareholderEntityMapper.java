package com.kartaguez.bluepot.infrastructure.down.repository.jpa.mapper;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.domain.model.PotShareholder;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity.PotShareholderEntity;
import com.kartaguez.bluepot.utils.Constants;

import lombok.NonNull;

@Component
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
