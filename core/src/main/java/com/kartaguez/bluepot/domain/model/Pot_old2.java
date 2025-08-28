package com.kartaguez.bluepot.domain.model;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang3.math.Fraction;

import com.kartaguez.bluepot.domain.model.superclass.VersionedObject2;
import com.kartaguez.bluepot.domain.record.PotRecord;
import com.kartaguez.bluepot.domain.record.PotShareholderRecord;
import com.kartaguez.bluepot.utils.Constants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@EqualsAndHashCode(callSuper=false)
@ToString
public class Pot_old2 extends VersionedObject2<PotRecord> {

    private String name;

    private HashMap<UUID, PotShareholder_old2> potShareholders;
    // private HashMap<UUID, Expense> expenses;

    private Pot_old2(@NonNull PotGlobalVersion _potGlobalVersion, PotRecord potRecord, List<PotShareholderRecord> potShareholderRecords) {
        super(_potGlobalVersion, potRecord);

        if (null == _potGlobalVersion.getPotUuid()) {
            throw new IllegalArgumentException("PotGlobalVersion PotUuid cannot be null.");
        }

        if (potRecord != null && !_potGlobalVersion.getPotUuid().equals(potRecord.uuid())) {
            throw new IllegalArgumentException("Pot Uuid does not match PotGlobalVersion PotUuid.");
        }

        this.uuid = _potGlobalVersion.getPotUuid();
        if (potRecord != null) {
            this.deleted = potRecord.deleted();
            this.name = potRecord.name();
        }

        this.potShareholders = new HashMap<UUID, PotShareholder_old2>();
        if (potShareholderRecords != null) {
            potShareholderRecords.stream().map(potShareholderRecord -> this.potShareholders.put(potShareholderRecord.uuid(), PotShareholder_old2.hydrateFromRecord(_potGlobalVersion, potShareholderRecord)));
        }

        this.setCurrentVersionRecordAsBaseTargetVersionRecord();
    }

    public static Pot_old2 hydrateFromRecords(@NonNull PotGlobalVersion _PotGlobalVersion, PotRecord potRecord, List<PotShareholderRecord> potShareholderRecords) {
        return new Pot_old2(_PotGlobalVersion, potRecord, potShareholderRecords);
    }

    public static Pot_old2 create(@NonNull String _name) {
        if (Constants.EMPTY_STRING.equals(_name)) {
            throw new IllegalArgumentException("Pot name cannot be empty.");
        }

        UUID newPotUuid = UUID.randomUUID();
        PotGlobalVersion potGlobalVersion = PotGlobalVersion.forNewPot(newPotUuid);
        Pot_old2 newPot = new Pot_old2(potGlobalVersion, null, null);
        
        newPot.uuid = newPotUuid;

        newPot.rename(_name);

        return newPot;
    }

    public void rename(@NonNull String _name) {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Pot deleted.");
        }
        if (Constants.EMPTY_STRING.equals(_name)) {
            throw new IllegalArgumentException("Pot name cannot be empty.");
        }

        this.name = _name;

        this.updateTargetVersionRecord();
    }

    public UUID addPotShareholder(@NonNull String name) {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Pot deleted.");
        }
        if (Constants.EMPTY_STRING.equals(name)) {
            throw new IllegalArgumentException("PotShareholder name cannot be empty.");
        }

        PotShareholder_old2 newPotShareholder = PotShareholder_old2.create(this.potGlobalVersion, name);

        if (null == this.potShareholders) {
            this.potShareholders = new HashMap<UUID, PotShareholder_old2>();
        }
        this.potShareholders.put(newPotShareholder.getUuid(), newPotShareholder);
 
        return newPotShareholder.getUuid();
    }

    public UUID updatePotShareholder(@NonNull UUID potShareholderUuid, @NonNull String _name) {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Pot deleted.");
        }
        PotShareholder_old2 potShareholder = this.potShareholders.get(potShareholderUuid);
        if (null == potShareholder) {
            throw new IllegalArgumentException("UUID matches no PotShareholder in the pot.");
        }
        potShareholder.rename(_name);

        return potShareholder.getUuid();
    }

    public void addExpense(@NonNull UUID payerUuid, @NonNull HashMap<UUID, Fraction> payeeWeights, @NonNull Fraction amount, @NonNull String label) {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Pot deleted.");
        }
        if (Fraction.ZERO.equals(amount)) {
            throw new IllegalArgumentException("Expense amount cannot be 0.");
        }
        if (Constants.EMPTY_STRING.equals(label)) {
            throw new IllegalArgumentException("Expense label cannot be empty.");
        }
        if (!this.potShareholders.keySet().contains(payerUuid)) {
            throw new IllegalArgumentException("Payer does not belong to the pot.");
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

        // TODO Implement
    }

    public void updateExpense(@NonNull UUID expenseUuid, UUID payerUuid, HashMap<UUID, Fraction> payeeWeights, Fraction amount, String label) {
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

        // TODO Implement
    }

    public void deleteExpense(@NonNull UUID expenseUuid) {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Expense already deleted.");
        }
        // TODO Implement
    }

    @Override
    protected PotRecord createTargetVersionRecord() {
        return new PotRecord(this.uuid, this.deleted, this.potGlobalVersion.getTargetPotVersion(), Constants.NULL_VERSION, this.name);
    }

    @Override
    protected void cascadeSetCurrentVersionRecordAsBaseTargetVersionRecord() {
        if (null != this.potShareholders) {
            this.potShareholders.values().stream().forEach(potShareholder -> potShareholder.setCurrentVersionRecordAsBaseTargetVersionRecord());
        }
    }

}
