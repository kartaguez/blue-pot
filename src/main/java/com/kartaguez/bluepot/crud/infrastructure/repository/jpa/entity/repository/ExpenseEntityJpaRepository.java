package com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;

import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.object.ExpenseEntity;

public interface ExpenseEntityJpaRepository extends ListCrudRepository<ExpenseEntity, Long> {

    public ExpenseEntity findByUuid(UUID uuid);

    public List<ExpenseEntity> findAllByPotUuid(UUID potUuid);

}
