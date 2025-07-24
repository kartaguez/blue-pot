package com.kartaguez.bluepot.crud.domain.model;

import java.util.UUID;

import org.apache.commons.lang3.math.Fraction;

import com.kartaguez.bluepot.crud.domain.model.mutation.ExpenseShareholderMutationResultSet;
import com.kartaguez.bluepot.crud.domain.model.superclass.VersionedObject;
import com.kartaguez.bluepot.crud.utils.Constants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
public class ExpenseShareholder extends VersionedObject {

    public UUID expenseUuid;
    public UUID potShareholderUuid;
    
    public Fraction weight;

    public ExpenseShareholder(@NonNull UUID _uuid, @NonNull UUID _expenseUuid, @NonNull UUID _potShareholderUuid, long _targetGlobalVersion, long _createdAtVersion, long _deletedAtVersion, @NonNull Fraction _weight) {
        this.uuid = _uuid;
        this.expenseUuid = _expenseUuid;
        this.potShareholderUuid = _potShareholderUuid;
        this.targetGlobalVersion = _targetGlobalVersion;
        this.createdAtVersion = _createdAtVersion;
        this.deletedAtVersion = _deletedAtVersion;
        this.weight = _weight;
    }

    public static ExpenseShareholderMutationResultSet create(@NonNull UUID _expenseUuid, @NonNull UUID _potShareholderUuid, long _targetGlobalVersion, @NonNull Fraction _weight) {
        if (Fraction.ZERO.equals(_weight)) {
            throw new IllegalArgumentException("Expense Shareholder weight cannot be 0.");
        }
        return new ExpenseShareholderMutationResultSet(null, new ExpenseShareholder(UUID.randomUUID(), _expenseUuid, _potShareholderUuid, _targetGlobalVersion, _targetGlobalVersion, Constants.NULL_VERSION, _weight));
    }

    public ExpenseShareholderMutationResultSet update(long _targetGlobalVersion, @NonNull Fraction _weight) {
        if (Fraction.ZERO.equals(_weight)) {
            throw new IllegalArgumentException("Expense Shareholder weight cannot be 0.");
        }
        this.delete();
        return new ExpenseShareholderMutationResultSet(this, new ExpenseShareholder(this.uuid, this.expenseUuid, this.potShareholderUuid, this.targetGlobalVersion, this.targetGlobalVersion, Constants.NULL_VERSION, _weight));
    }
}
