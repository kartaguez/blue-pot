package com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.repository;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.object.PotEntity;

@Repository
public interface PotEntityJpaRepository extends ListCrudRepository<PotEntity, Long> {

    public PotEntity findByUuid(UUID uuid);

}
