package com.kartaguez.bluepot.infrastructure.down.repository.jpa.mapper;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.domain._to_delete.model.PotGlobalVersion;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity.PotGlobalVersionEntity;

import lombok.NonNull;

@Component
public class PotGlobalVersionEntityMapper {

    public PotGlobalVersion toDomain(@NonNull PotGlobalVersionEntity potGlobalVersionEntity) {
        return null;
    }

    public PotGlobalVersionEntity toEntity(@NonNull PotGlobalVersion potGlobalVersion) {
        return null;
    }

}
