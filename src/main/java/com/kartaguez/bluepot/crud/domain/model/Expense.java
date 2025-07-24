package com.kartaguez.bluepot.crud.domain.model;

import java.util.HashMap;
import java.util.UUID;

import org.apache.commons.lang3.math.Fraction;

import com.kartaguez.bluepot.crud.domain.model.mutation.ExpenseMutationResultSet;
import com.kartaguez.bluepot.crud.domain.model.mutation.ExpenseShareholderMutationResultSet;
import com.kartaguez.bluepot.crud.domain.model.superclass.VersionedObject;
import com.kartaguez.bluepot.crud.utils.Constants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
public class Expense extends VersionedObject {

    private UUID potUuid;

    private UUID payerUuid;
    private String label;
    private Fraction amount;

    private HashMap<UUID, ExpenseShareholder> expenseShareholders;

    public Expense(@NonNull UUID _uuid, @NonNull UUID _potUuid,  long _targetGlobalVersion, long _createdAtVersion, long _deletedAtVersion, @NonNull UUID _payerUuid, @NonNull Fraction _amount, @NonNull String _label, @NonNull HashMap<UUID, ExpenseShareholder> _expenseShareholders) {
        this.uuid = _uuid;
        this.potUuid = _potUuid;
        this.targetGlobalVersion = _targetGlobalVersion;
        this.createdAtVersion = _createdAtVersion;
        this.deletedAtVersion = _deletedAtVersion;
        this.payerUuid = _payerUuid;
        this.amount = _amount;
        this.label = _label;
        this.expenseShareholders = _expenseShareholders;
    }

    public static ExpenseMutationResultSet create(@NonNull UUID _potUuid, long _targetGlobalVersion, @NonNull UUID _payerUuid, @NonNull Fraction _amount, @NonNull String _label) {
        if (Fraction.ZERO.equals(_amount)) {
            throw new IllegalArgumentException("Expense amount cannot be 0.");
        }
        if (Constants.EMPTY_STRING.equals(_label)) {
            throw new IllegalArgumentException("Expense label cannot be empty.");
        }
        Expense createdExpense = new Expense(UUID.randomUUID(), _potUuid, _targetGlobalVersion, _targetGlobalVersion, Constants.NULL_VERSION, _payerUuid, _amount, _label, null);
        return new ExpenseMutationResultSet(null, createdExpense, null);
    }

    public ExpenseMutationResultSet update(UUID _payerUuid, Fraction _amount, String _label) {
        UUID newPayerUuid = this.payerUuid;
        if (null != _payerUuid) {
            newPayerUuid = _payerUuid;
        }
        Fraction newAmount = this.amount;
        if (null != _amount) {
            if (Fraction.ZERO.equals(_amount)) {
                throw new IllegalArgumentException("Expense amount cannot be 0.");
            } else {
                newAmount = _amount;
            }
        }
        String newLabel = this.label;
        if (null != _label) {
            if (Constants.EMPTY_STRING.equals(_label)) {
                throw new IllegalArgumentException("Expense label cannot be empty.");
            } else {
                newLabel = _label;
            }
        }
        Expense updatedExpense = new Expense(this.uuid, this.potUuid, this.targetGlobalVersion, this.targetGlobalVersion, Constants.NULL_VERSION, newPayerUuid, newAmount, newLabel, this.expenseShareholders);
        this.markAsDeleted();
        
        return new ExpenseMutationResultSet(this, updatedExpense, null);
    }

    public ExpenseShareholderMutationResultSet addShareholder(@NonNull UUID potShareholderUuid, @NonNull Fraction weight) {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Expense deleted.");
        }
        ExpenseShareholder expenseShareholder = this.expenseShareholders.get(potShareholderUuid);
        if (null != expenseShareholder) {
            throw new IllegalArgumentException("PotShareholder already declared on this expense.");
        }
        ExpenseShareholderMutationResultSet expenseShareholderMutationResultSet = ExpenseShareholder.create(this.uuid, potShareholderUuid, this.targetGlobalVersion, weight);
        ExpenseShareholder newExpenseShareholder = expenseShareholderMutationResultSet.getNewExpenseShareholderInstance();
        this.expenseShareholders.put(potShareholderUuid, newExpenseShareholder);
        
        return expenseShareholderMutationResultSet;
    }

    public ExpenseShareholderMutationResultSet updateShareholder(@NonNull UUID potShareholderUuid, @NonNull Fraction weight) {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Expense deleted.");
        }
        ExpenseShareholder expenseShareholder = this.expenseShareholders.get(potShareholderUuid);
        if (null == expenseShareholder) {
            throw new IllegalArgumentException("PotShareholder not declared on this expense.");
        }
        ExpenseShareholderMutationResultSet expenseShareholderMutationResultSet = expenseShareholder.update(this.targetGlobalVersion, weight);
        
        ExpenseShareholder updatedExpenseShareholder = expenseShareholderMutationResultSet.getNewExpenseShareholderInstance();
        this.expenseShareholders.put(potShareholderUuid, updatedExpenseShareholder);
        
        return expenseShareholderMutationResultSet;
    }

    public ExpenseShareholderMutationResultSet removeShareholder(@NonNull UUID potShareholderUuid) {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Expense deleted.");
        }
        ExpenseShareholder expenseShareholder = this.expenseShareholders.get(potShareholderUuid);
        if (null == expenseShareholder) {
            throw new IllegalArgumentException("PotShareholder not declared on this expense.");
        }
        expenseShareholder.markAsDeleted();
        this.expenseShareholders.remove(potShareholderUuid);

        return new ExpenseShareholderMutationResultSet(expenseShareholder, null);
    }

}
