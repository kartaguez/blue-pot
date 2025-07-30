package com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.object.PotShareholderEntity;

@Repository
public interface PotShareholderEntityJpaRepository extends ListCrudRepository<PotShareholderEntity, Long> {

    public PotShareholderEntity findByUuid(UUID uuid);

    public List<PotShareholderEntity> findAllByPotUuid(UUID potUuid);

}
