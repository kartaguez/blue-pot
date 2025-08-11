package com.kartaguez.bluepot.infrastructure.down.repository.jpa.wrapped;

import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity.PotEntity;

@Repository
public interface PotEntityJpaRepository extends ListCrudRepository<PotEntity, Long> {

    public PotEntity findByUuid(UUID uuid);

}
