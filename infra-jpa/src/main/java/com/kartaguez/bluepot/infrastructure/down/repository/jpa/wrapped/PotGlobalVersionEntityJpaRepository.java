package com.kartaguez.bluepot.infrastructure.down.repository.jpa.wrapped;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity.PotGlobalVersionEntity;

@Repository
public interface PotGlobalVersionEntityJpaRepository extends ListCrudRepository<PotGlobalVersionEntity, Long> {

    public PotGlobalVersionEntity findByPotUuid(UUID potUuid);

}
