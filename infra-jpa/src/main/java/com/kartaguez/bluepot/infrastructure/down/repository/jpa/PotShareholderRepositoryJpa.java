package com.kartaguez.bluepot.infrastructure.down.repository.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.application.down.repository.PotShareholderRepository;
import com.kartaguez.bluepot.domain._to_delete.model.PotShareholder_old1;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity.PotShareholderEntity;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.mapper.PotShareholderEntityMapper;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.wrapped.PotShareholderEntityJpaRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PotShareholderRepositoryJpa implements PotShareholderRepository {

    private final PotShareholderEntityJpaRepository potShareholderEntityJpaRepository;
    private final PotShareholderEntityMapper potShareholderEntityMapper;

    @Override
    public PotShareholder_old1 loadPotShareholderByUuid(@NonNull UUID uuid, long targetGlobalVersion) {
        PotShareholderEntity potShareholderEntity = this.potShareholderEntityJpaRepository.findByUuid(uuid);
        return this.potShareholderEntityMapper.toDomain(potShareholderEntity, targetGlobalVersion);
    }

    private PotShareholder_old1 save(@NonNull PotShareholder_old1 potShareholder) {
        PotShareholderEntity potShareholderEntity = this.potShareholderEntityMapper.toEntity(potShareholder);
        potShareholderEntity = this.potShareholderEntityJpaRepository.save(potShareholderEntity);
        return this.potShareholderEntityMapper.toDomain(potShareholderEntity, potShareholder.getNextGlobalVersion());
    }

    @Override
    public Collection<PotShareholder_old1> saveAll(@NonNull Collection<PotShareholder_old1> potShareholders) {
        Collection<PotShareholder_old1> potShareholderEntities = new ArrayList<PotShareholder_old1>();
        potShareholders.stream().forEach(potShareholder -> potShareholderEntities.add(this.save(potShareholder)));
        return potShareholderEntities;
    }

}
