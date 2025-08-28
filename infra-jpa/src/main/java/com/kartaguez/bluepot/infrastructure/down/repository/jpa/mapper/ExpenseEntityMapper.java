package com.kartaguez.bluepot.infrastructure.down.repository.jpa.mapper;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.domain.model.Expense;
import com.kartaguez.bluepot.domain.model.Pot_old1;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity.ExpenseEntity;

import lombok.NonNull;

@Component
public class ExpenseEntityMapper {

    public Pot_old1 toDomain(@NonNull ExpenseEntity expenseEntity, long targetGlobalVersion) {
        // TODO
        return null;
    }

    
    public ExpenseEntity toEntity(@NonNull Expense expense) {
        // TODO
        return null;
    }

}
