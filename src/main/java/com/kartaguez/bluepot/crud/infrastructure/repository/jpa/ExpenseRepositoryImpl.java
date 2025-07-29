package com.kartaguez.bluepot.crud.infrastructure.repository.jpa;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.crud.domain.bottom.repository.ExpenseRepository;
import com.kartaguez.bluepot.crud.domain.model.object.Expense;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.mapper.ExpenseEntityMapper;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.repository.ExpenseEntityJpaRepository;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@Data
@RequiredArgsConstructor
public class ExpenseRepositoryImpl implements ExpenseRepository {

    private ExpenseEntityJpaRepository expenseEntityJpaRepository;
    private ExpenseEntityMapper expenseEntityMapper;

    public Expense loadByUuid(@NonNull UUID uuid, long targetGlobalVersion) {
        throw new UnsupportedOperationException("Unimplemented method 'loadByUuid'");
    }

    @Override
    public void save(@NonNull Expense expense) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
