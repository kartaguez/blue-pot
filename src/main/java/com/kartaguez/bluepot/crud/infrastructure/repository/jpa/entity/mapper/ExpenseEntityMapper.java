package com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.mapper;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.crud.domain.model.object.Expense;
import com.kartaguez.bluepot.crud.domain.model.object.Pot;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.object.ExpenseEntity;

import lombok.NonNull;

@Component
public class ExpenseEntityMapper {

    public Pot toDomain(@NonNull ExpenseEntity expenseEntity, long targetGlobalVersion) {
        // TODO
        return null;
    }

    
    public ExpenseEntity toEntity(@NonNull Expense expense) {
        // TODO
        return null;
    }

}
