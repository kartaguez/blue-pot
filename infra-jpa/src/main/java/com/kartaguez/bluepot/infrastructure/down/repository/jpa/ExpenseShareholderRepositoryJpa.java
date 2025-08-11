package com.kartaguez.bluepot.infrastructure.down.repository.jpa;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.application.down.repository.ExpenseShareholderRepository;
import com.kartaguez.bluepot.domain.model.ExpenseShareholder;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.mapper.ExpenseShareholderEntityMapper;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.wrapped.ExpenseShareholderEntityJpaRepository;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ExpenseShareholderRepositoryJpa implements ExpenseShareholderRepository {

    private final ExpenseShareholderEntityJpaRepository expenseShareholderEntityJpaRepository;
    private final ExpenseShareholderEntityMapper expenseShareholderEntityMapper;

    public ExpenseShareholder loadByUuid(@NonNull UUID uuid, long targetGlobalVersion) {
        throw new UnsupportedOperationException("Unimplemented method 'loadByUuid'");
    }

    @Override
    public ExpenseShareholder save(@NonNull ExpenseShareholder expenseShareholder) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
