package com.kartaguez.bluepot.infrastructure.down.repository.jpa;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.application.down.repository.ExpenseRepository;
import com.kartaguez.bluepot.domain.model.Expense;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.mapper.ExpenseEntityMapper;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.wrapped.ExpenseEntityJpaRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExpenseRepositoryJpa implements ExpenseRepository {

    private final ExpenseEntityJpaRepository expenseEntityJpaRepository;
    private final ExpenseEntityMapper expenseEntityMapper;

    public Expense loadByUuid(@NonNull UUID uuid, long targetGlobalVersion) {
        throw new UnsupportedOperationException("Unimplemented method 'loadByUuid'");
    }

    @Override
    public Expense save(@NonNull Expense expense) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
