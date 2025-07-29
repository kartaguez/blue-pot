package com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.repository;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.object.PotEntity;

public interface PotEntityJpaRepository extends ListCrudRepository<PotEntity, Long> {

    public PotEntity findByUuid(UUID uuid);

}
