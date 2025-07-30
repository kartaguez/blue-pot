package com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;

import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.object.ExpenseShareholderEntity;

@Repository
public interface ExpenseShareholderEntityJpaRepository extends ListCrudRepository<ExpenseShareholderEntity, Long> {

    public ExpenseShareholderEntity findByUuid(UUID uuid);

    public List<ExpenseShareholderEntity> findAllByExpenseUuid(UUID expenseUuid);

}
