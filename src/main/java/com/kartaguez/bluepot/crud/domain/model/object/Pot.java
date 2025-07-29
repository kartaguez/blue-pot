package com.kartaguez.bluepot.crud.domain.model.object;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.math.Fraction;

import com.kartaguez.bluepot.crud.domain.model.mutation.ExpenseMutationResultSet;
import com.kartaguez.bluepot.crud.domain.model.mutation.ExpenseShareholderMutationResultSet;
import com.kartaguez.bluepot.crud.domain.model.mutation.PotMutationResultSet;
import com.kartaguez.bluepot.crud.domain.model.mutation.PotShareholderMutationResultSet;
import com.kartaguez.bluepot.crud.domain.model.object.superclass.VersionedObject;
import com.kartaguez.bluepot.crud.utils.Constants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
public class Pot extends VersionedObject {

    private String name;

    private HashMap<UUID, PotShareholder> potShareholders;
    private HashMap<UUID, Expense> expenses;

    private Pot(@NonNull UUID _uuid, long _targetGlobalVersion, long _createdAtVersion, long _deletedAtVersion, @NonNull String _name, HashMap<UUID, PotShareholder> _potShareholders, HashMap<UUID, Expense> _expenses) {
        this.uuid = _uuid;
        this.targetGlobalVersion = _targetGlobalVersion;
        this.createdAtVersion = _createdAtVersion;
        this.deletedAtVersion = _deletedAtVersion;
        this.name = _name;
        if (null == _potShareholders) {
            this.potShareholders = new HashMap<UUID, PotShareholder>();
        } else {
            this.potShareholders = _potShareholders;
        }
        if (null == _expenses) {
            this.expenses = new HashMap<UUID, Expense>();
        } else {
            this.expenses = _expenses;
        }
    }

    public static Pot hydrateAgregate(@NonNull UUID _uuid, long _targetGlobalVersion, long _createdAtVersion, long _deletedAtVersion, @NonNull String _name, HashMap<UUID, PotShareholder> _potShareholders, HashMap<UUID, Expense> _expenses) {
        return new Pot(_uuid, _targetGlobalVersion, _createdAtVersion, _deletedAtVersion, _name, _potShareholders, _expenses);
    }

    public static Pot hydrateRoot(@NonNull UUID _uuid, long _targetGlobalVersion, long _createdAtVersion, long _deletedAtVersion, @NonNull String _name) {
        return new Pot(_uuid, _targetGlobalVersion, _createdAtVersion, _deletedAtVersion, _name, null, null);
    }

    public static PotMutationResultSet createRoot(@NonNull String _name) {
        if (Constants.EMPTY_STRING.equals(_name)) {
            throw new IllegalArgumentException("Pot name cannot be empty.");
        }
        Pot createdPot = new Pot(UUID.randomUUID(), Constants.FIRST_VERSION, Constants.FIRST_VERSION, Constants.NULL_VERSION, _name, null, null);
        return new PotMutationResultSet(null, createdPot, null);
    }

    public PotMutationResultSet updateRoot(@NonNull String _name) {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Pot deleted.");
        }
        if (Constants.EMPTY_STRING.equals(_name)) {
            throw new IllegalArgumentException("Pot name cannot be empty.");
        }
        this.markAsDeleted();
        Pot renamedPot = new Pot(this.uuid, this.targetGlobalVersion, this.targetGlobalVersion, Constants.NULL_VERSION, _name, this.potShareholders, this.expenses);
        return new PotMutationResultSet(this, renamedPot, null);
    }

    public PotShareholderMutationResultSet addPotShareholder(@NonNull String name) {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Pot deleted.");
        }
        PotShareholderMutationResultSet potShareholderMutationResultSet = PotShareholder.create(this.uuid, this.targetGlobalVersion, name);
        PotShareholder newPotShareholder = potShareholderMutationResultSet.getNewPotShareholderInstance();
        this.potShareholders.put(newPotShareholder.getUuid(), newPotShareholder);
        return potShareholderMutationResultSet;
    }

    public PotShareholderMutationResultSet updatePotShareholder(@NonNull UUID potShareholderUuid, @NonNull String _name) {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Pot deleted.");
        }
        PotShareholder potShareholder = this.potShareholders.get(potShareholderUuid);
        if (null == potShareholder) {
            throw new IllegalArgumentException("UUID matches no PotShareholder in the pot.");
        }
        PotShareholderMutationResultSet potShareholderMutationResultSet = potShareholder.rename(_name);
        
        PotShareholder updatedPotShareholder = potShareholderMutationResultSet.getNewPotShareholderInstance();
        this.potShareholders.put(potShareholderUuid, updatedPotShareholder);
        
        return potShareholderMutationResultSet;
    }

    public ExpenseMutationResultSet addExpense(@NonNull UUID payerUuid, @NonNull HashMap<UUID, Fraction> payeeWeights, @NonNull Fraction amount, @NonNull String label) {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Pot deleted.");
        }
        if (Fraction.ZERO.equals(amount)) {
            throw new IllegalArgumentException("Expense amount cannot be 0.");
        }
        if (Constants.EMPTY_STRING.equals(label)) {
            throw new IllegalArgumentException("Expense label cannot be empty.");
        }
        if (0 == payeeWeights.size()) {
            throw new IllegalArgumentException("Payees list cannot be empty.");
        }
        if (!this.potShareholders.keySet().containsAll(payeeWeights.keySet())) {
            throw new IllegalArgumentException("At least one payee does not belong to the pot.");
        }
        if (payeeWeights.values().contains(Fraction.ZERO)) {
            throw new IllegalArgumentException("Payee weight cannot be 0.");
        }

        ExpenseMutationResultSet expenseMutationResultSet = Expense.createRoot(this.uuid, this.targetGlobalVersion, payerUuid, amount, label);
        Expense newExpense = expenseMutationResultSet.getNewExpenseInstance();
        List<ExpenseShareholderMutationResultSet> ExpenseShareholderMutationResultSets = new ArrayList<ExpenseShareholderMutationResultSet>(payeeWeights.size());
        payeeWeights.keySet().stream().forEach(potShareholderUuid -> ExpenseShareholderMutationResultSets.add(newExpense.addShareholder(potShareholderUuid, payeeWeights.get(potShareholderUuid))));
        return new ExpenseMutationResultSet(null, newExpense, ExpenseShareholderMutationResultSets);
    }

    public ExpenseMutationResultSet updateExpense(@NonNull UUID expenseUuid, UUID payerUuid, HashMap<UUID, Fraction> payeeWeights, Fraction amount, String label) {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Pot deleted.");
        }
        if (null != amount && Fraction.ZERO.equals(amount)) {
            throw new IllegalArgumentException("Expense amount cannot be 0.");
        }
        if (null != label && Constants.EMPTY_STRING.equals(label)) {
            throw new IllegalArgumentException("Expense label cannot be empty.");
        }
        if (null != payeeWeights) {
            if (0 == payeeWeights.size()) {
                throw new IllegalArgumentException("Payees list cannot be empty.");
            }
            if (!this.potShareholders.keySet().containsAll(payeeWeights.keySet())) {
                throw new IllegalArgumentException("At least one payee does not belong to the pot.");
            }
            if (payeeWeights.values().contains(Fraction.ZERO)) {
                throw new IllegalArgumentException("Payee weight cannot be 0.");
            }
        }

        Expense expense = this.expenses.get(expenseUuid);
        if (null == expense) {
            throw new IllegalArgumentException("Expense not declared on this pot.");
        }
        ExpenseMutationResultSet expenseMutationResultSet = expense.updateRoot(payerUuid, amount, label);
        
        Expense newExpense = expenseMutationResultSet.getNewExpenseInstance();
        List<ExpenseShareholderMutationResultSet> finalExpenseShareholderMutationResultSets = null;
        
        if (null != payeeWeights) {
            List<ExpenseShareholderMutationResultSet> expenseShareholderMutationResultSets = new ArrayList<ExpenseShareholderMutationResultSet>(expense.getExpenseShareholders().size() + payeeWeights.size());
        
            payeeWeights.keySet().stream().forEach(potShareholderUuid -> expenseShareholderMutationResultSets.add(newExpense.addShareholder(potShareholderUuid, payeeWeights.get(potShareholderUuid))));
            newExpense.getExpenseShareholders().keySet().stream().forEach(potShareholderUuid -> expenseShareholderMutationResultSets.add(newExpense.removeShareholder(potShareholderUuid)));

            finalExpenseShareholderMutationResultSets = expenseShareholderMutationResultSets;
        }

        expense.markAsDeleted();
        this.expenses.put(expenseUuid, newExpense);
        
        return new ExpenseMutationResultSet(expense, newExpense, finalExpenseShareholderMutationResultSets);
    }

    public ExpenseMutationResultSet deleteExpense(@NonNull UUID expenseUuid) {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Expense already deleted.");
        }
        Expense expense = this.expenses.get(expenseUuid);
        if (null == expense) {
            throw new IllegalArgumentException("Expense not declared on this pot.");
        }

        List<ExpenseShareholderMutationResultSet> expenseShareholderMutationResultSets = new ArrayList<ExpenseShareholderMutationResultSet>(expense.getExpenseShareholders().size());
        
        expense.getExpenseShareholders().keySet().stream().forEach(potShareholderUuid -> expenseShareholderMutationResultSets.add(expense.removeShareholder(potShareholderUuid)));
        expense.markAsDeleted();
        
        return new ExpenseMutationResultSet(expense, null, expenseShareholderMutationResultSets);
    }

}
