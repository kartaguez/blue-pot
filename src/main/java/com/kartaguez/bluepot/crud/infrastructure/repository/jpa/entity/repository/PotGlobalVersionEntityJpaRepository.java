package com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.repository;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.object.PotGlobalVersionEntity;

@Repository
public interface PotGlobalVersionEntityJpaRepository extends ListCrudRepository<PotGlobalVersionEntity, Long> {

    public PotGlobalVersionEntity findByPotUuid(UUID potUuid);

}
