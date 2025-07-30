package com.kartaguez.bluepot.crud.infrastructure.repository.jpa;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.crud.domain.bottom.repository.PotRepository;
import com.kartaguez.bluepot.crud.domain.model.object.Pot;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.mapper.PotEntityMapper;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.object.PotEntity;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.repository.PotEntityJpaRepository;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PotRepositoryJpa implements PotRepository {

    private PotEntityJpaRepository potEntityJpaRepository;
    private PotEntityMapper potEntityMapper;

    @Override
    public Pot loadPotByUuid(@NonNull UUID uuid, long targetGlobalVersion) {
        PotEntity potEntity = this.potEntityJpaRepository.findByUuid(uuid);
        return this.potEntityMapper.toDomain(potEntity, targetGlobalVersion);
    }

    @Override
    public Pot save(@NonNull Pot pot) {
        PotEntity potEntity = this.potEntityMapper.toEntity(pot);
        potEntity = this.potEntityJpaRepository.save(potEntity);
        return this.potEntityMapper.toDomain(potEntity, pot.getTargetGlobalVersion());
    }

}
