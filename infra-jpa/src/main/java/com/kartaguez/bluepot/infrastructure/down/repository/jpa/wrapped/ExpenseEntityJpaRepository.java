package com.kartaguez.bluepot.infrastructure.down.repository.jpa.wrapped;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity.ExpenseEntity;

@Repository
public interface ExpenseEntityJpaRepository extends ListCrudRepository<ExpenseEntity, Long> {

    public ExpenseEntity findByUuid(UUID uuid);

    public List<ExpenseEntity> findAllByPotUuid(UUID potUuid);

}
