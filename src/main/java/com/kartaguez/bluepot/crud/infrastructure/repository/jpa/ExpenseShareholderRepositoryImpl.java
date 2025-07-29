package com.kartaguez.bluepot.crud.infrastructure.repository.jpa;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.crud.domain.bottom.repository.ExpenseShareholderRepository;
import com.kartaguez.bluepot.crud.domain.model.object.ExpenseShareholder;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.mapper.ExpenseShareholderEntityMapper;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.repository.ExpenseShareholderEntityJpaRepository;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Component
@Data
@RequiredArgsConstructor
public class ExpenseShareholderRepositoryImpl implements ExpenseShareholderRepository {

    private ExpenseShareholderEntityJpaRepository expenseShareholderEntityJpaRepository;
    private ExpenseShareholderEntityMapper expenseShareholderEntityMapper;

    public ExpenseShareholder loadByUuid(@NonNull UUID uuid, long targetGlobalVersion) {
        throw new UnsupportedOperationException("Unimplemented method 'loadByUuid'");
    }

    @Override
    public void save(@NonNull ExpenseShareholder expenseShareholder) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'save'");
    }

}
