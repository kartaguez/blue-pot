package com.kartaguez.bluepot.infrastructure.down.repository.jpa;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.application.down.repository.PotGlobalVersionRepository;
import com.kartaguez.bluepot.domain.model.PotGlobalVersion;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity.PotGlobalVersionEntity;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.mapper.PotGlobalVersionEntityMapper;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.wrapped.PotGlobalVersionEntityJpaRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PotGlobalVersionRepositoryJpa implements PotGlobalVersionRepository {

    private final PotGlobalVersionEntityJpaRepository potGlobalVersionEntityJpaRepository;
    private final PotGlobalVersionEntityMapper potGlobalVersionEntityMapper;

    @Override
    public PotGlobalVersion loadPotGlobalVersionByPotUuid(UUID potUuid) {
        PotGlobalVersionEntity potGlobalVersionEntity = this.potGlobalVersionEntityJpaRepository.findByPotUuid(potUuid);
        return this.potGlobalVersionEntityMapper.toDomain(potGlobalVersionEntity);
    }

    @Override
    public PotGlobalVersion save(PotGlobalVersion potGlobalVersion) {
        PotGlobalVersionEntity potGlobalVersionEntity = this.potGlobalVersionEntityMapper.toEntity(potGlobalVersion);
        potGlobalVersionEntity = this.potGlobalVersionEntityJpaRepository.save(potGlobalVersionEntity);
        return this.potGlobalVersionEntityMapper.toDomain(potGlobalVersionEntity);
    }

}
