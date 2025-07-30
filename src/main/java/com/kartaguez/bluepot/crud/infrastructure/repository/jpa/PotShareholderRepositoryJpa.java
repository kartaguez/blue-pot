package com.kartaguez.bluepot.crud.infrastructure.repository.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.crud.domain.bottom.repository.PotShareholderRepository;
import com.kartaguez.bluepot.crud.domain.model.object.PotShareholder;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.mapper.PotShareholderEntityMapper;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.object.PotShareholderEntity;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.repository.PotShareholderEntityJpaRepository;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PotShareholderRepositoryJpa implements PotShareholderRepository {

    private PotShareholderEntityJpaRepository potShareholderEntityJpaRepository;
    private PotShareholderEntityMapper potShareholderEntityMapper;

    @Override
    public PotShareholder loadPotShareholderByUuid(@NonNull UUID uuid, long targetGlobalVersion) {
        PotShareholderEntity potShareholderEntity = this.potShareholderEntityJpaRepository.findByUuid(uuid);
        return this.potShareholderEntityMapper.toDomain(potShareholderEntity, targetGlobalVersion);
    }

    private PotShareholder save(@NonNull PotShareholder potShareholder) {
        PotShareholderEntity potShareholderEntity = this.potShareholderEntityMapper.toEntity(potShareholder);
        potShareholderEntity = this.potShareholderEntityJpaRepository.save(potShareholderEntity);
        return this.potShareholderEntityMapper.toDomain(potShareholderEntity, potShareholder.getTargetGlobalVersion());
    }

    @Override
    public Collection<PotShareholder> saveAll(@NonNull Collection<PotShareholder> potShareholders) {
        Collection<PotShareholder> potShareholderEntities = new ArrayList<PotShareholder>();
        potShareholders.stream().forEach(potShareholder -> potShareholderEntities.add(this.save(potShareholder)));
        return potShareholderEntities;
    }

}
