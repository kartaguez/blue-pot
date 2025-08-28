package com.kartaguez.bluepot.infrastructure.down.repository.jpa.mapper;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.domain.model.ExpenseShareholder;
import com.kartaguez.bluepot.domain.model.Pot_old1;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity.ExpenseShareholderEntity;

import lombok.NonNull;

@Component
public class ExpenseShareholderEntityMapper {

    public Pot_old1 toDomain(@NonNull ExpenseShareholderEntity expenseShareholderEntity, long targetGlobalVersion) {
        // TODO
        return null;
    }

    
    public ExpenseShareholderEntity toEntity(@NonNull ExpenseShareholder expenseShareholder) {
        // TODO
        return null;
    }

}
