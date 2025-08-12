package com.kartaguez.bluepot.domain.model;

import java.util.UUID;

import org.apache.commons.lang3.math.Fraction;

import com.kartaguez.bluepot.domain.model.mutation.ExpenseShareholderMutationResultSet;
import com.kartaguez.bluepot.domain.model.superclass.VersionedObject;
import com.kartaguez.bluepot.utils.Constants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper=false)
public class ExpenseShareholder extends VersionedObject {

    public UUID expenseUuid;
    public UUID potShareholderUuid;
    
    public Fraction weight;

    public ExpenseShareholder(@NonNull UUID _uuid, @NonNull UUID _expenseUuid, @NonNull UUID _potShareholderUuid, long _currentGlobalVersion, long _createdAtVersion, long _deletedAtVersion, @NonNull Fraction _weight) {
        this.uuid = _uuid;
        this.expenseUuid = _expenseUuid;
        this.potShareholderUuid = _potShareholderUuid;
        this.currentGlobalVersion = _currentGlobalVersion;
        this.createdAtVersion = _createdAtVersion;
        this.deletedAtVersion = _deletedAtVersion;
        this.weight = _weight;
    }

    public static ExpenseShareholder hydrateRoot(String _uuid, @NonNull String _expenseUuid, @NonNull String _potShareholderUuid,  long _currentGlobalVersion, long _createdAtVersion, long _deletedAtVersion, @NonNull String _weight) {
        UUID hUuid = null;
        if (null != _uuid) {
            hUuid = UUID.fromString(_uuid);
        }
        return new ExpenseShareholder(hUuid, UUID.fromString(_expenseUuid), UUID.fromString(_potShareholderUuid), _currentGlobalVersion, _createdAtVersion, _deletedAtVersion, Fraction.getFraction(_weight));
    }

    public static ExpenseShareholderMutationResultSet createRoot(@NonNull UUID _expenseUuid, @NonNull UUID _potShareholderUuid, long _currentGlobalVersion, @NonNull Fraction _weight) {
        if (Fraction.ZERO.equals(_weight)) {
            throw new IllegalArgumentException("Expense Shareholder weight cannot be 0.");
        }
        return new ExpenseShareholderMutationResultSet(null, new ExpenseShareholder(UUID.randomUUID(), _expenseUuid, _potShareholderUuid, _currentGlobalVersion, _currentGlobalVersion, Constants.NULL_VERSION, _weight));
    }

    public ExpenseShareholderMutationResultSet updateRoot(long _currentGlobalVersion, @NonNull Fraction _weight) {
        if (Fraction.ZERO.equals(_weight)) {
            throw new IllegalArgumentException("Expense Shareholder weight cannot be 0.");
        }
        this.markAsDeleted();
        return new ExpenseShareholderMutationResultSet(this, new ExpenseShareholder(this.uuid, this.expenseUuid, this.potShareholderUuid, this.currentGlobalVersion, this.currentGlobalVersion, Constants.NULL_VERSION, _weight));
    }
}
