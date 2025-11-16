package com.kartaguez.bluepot.infrastructure.down.repository.jpa.mapper;

import com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity.PotEntity;
import com.kartaguez.bluepot.model.Pot;

public class PotEntityMapper {

    public Pot toDomain(PotEntity potEntity) {
        if (null == potEntity) {
            throw new IllegalArgumentException("PotEntity cannot be null.");
        }
        return Pot.builder(potEntity.getUuid(), potEntity.getName()).build();
    }

    public PotEntity toEntity(Pot pot, Long activeFromBusinessVersionValue, Long inactiveFromBusinessVersionValue) {
        if (null == pot) {
            throw new IllegalArgumentException("Pot cannot be null.");
        }
        return new PotEntity(pot.getUuid(), activeFromBusinessVersionValue, inactiveFromBusinessVersionValue, pot.getName());
    }

}
