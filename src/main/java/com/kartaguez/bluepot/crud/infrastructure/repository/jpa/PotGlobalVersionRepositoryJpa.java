package com.kartaguez.bluepot.crud.infrastructure.repository.jpa;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.crud.domain.bottom.repository.PotGlobalVersionRepository;
import com.kartaguez.bluepot.crud.domain.model.object.PotGlobalVersion;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.mapper.PotGlobalVersionEntityMapper;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.object.PotGlobalVersionEntity;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.repository.PotGlobalVersionEntityJpaRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PotGlobalVersionRepositoryJpa implements PotGlobalVersionRepository {

    private PotGlobalVersionEntityJpaRepository potGlobalVersionEntityJpaRepository;
    private PotGlobalVersionEntityMapper potGlobalVersionEntityMapper;

    @Override
    public PotGlobalVersion loadPotGlobalVersionByPotUuid(UUID potUuid) {
        PotGlobalVersionEntity potGlobalVersionEntity = this.potGlobalVersionEntityJpaRepository.findByPotUuid(potUuid);
        return this.potGlobalVersionEntityMapper.toDomain(potGlobalVersionEntity);
    }

    @Override
    public void save(PotGlobalVersion potGlobalVersion) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
