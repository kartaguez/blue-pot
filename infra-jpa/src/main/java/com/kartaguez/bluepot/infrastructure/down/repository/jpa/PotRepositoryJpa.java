package com.kartaguez.bluepot.infrastructure.down.repository.jpa;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.application.down.repository.PotRepository;
import com.kartaguez.bluepot.domain.model.Pot;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity.PotEntity;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.mapper.PotEntityMapper;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.wrapped.PotEntityJpaRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PotRepositoryJpa implements PotRepository {

    private final PotEntityJpaRepository potEntityJpaRepository;
    private final PotEntityMapper potEntityMapper;

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
