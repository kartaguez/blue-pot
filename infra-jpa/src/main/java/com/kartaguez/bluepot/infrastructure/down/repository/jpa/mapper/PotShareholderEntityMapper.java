package com.kartaguez.bluepot.infrastructure.down.repository.jpa.mapper;

import com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity.PotShareholderEntity;
import com.kartaguez.bluepot.model.PotShareholder;

public class PotShareholderEntityMapper {

    public PotShareholder toDomain(PotShareholderEntity potShareholderEntity) {
        if (null == potShareholderEntity) {
            throw new IllegalArgumentException("potShareholderEntity cannot be null.");
        }
        return PotShareholder.builder(potShareholderEntity.getUuid(), potShareholderEntity.getPotUuid(), potShareholderEntity.getName()).build();
    }

    public PotShareholderEntity toEntity(PotShareholder potShareholder, Long activeFromBusinessVersionValue, Long inactiveFromBusinessVersionValue) {
        if (null == potShareholder) {
            throw new IllegalArgumentException("Pot cannot be null.");
        }
        return new PotShareholderEntity(potShareholder.getUuid(), potShareholder.getPotUuid(), activeFromBusinessVersionValue, inactiveFromBusinessVersionValue, potShareholder.getName());
    }

}
