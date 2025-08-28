package com.kartaguez.bluepot.domain._to_delete.model;

import java.util.UUID;

import org.apache.commons.lang3.math.Fraction;

import com.kartaguez.bluepot.domain._to_delete.model.superclass.VersionedObject2;
import com.kartaguez.bluepot.domain._to_delete.record.ExpenseShareholderRecord;
import com.kartaguez.bluepot.domain._to_delete.record.PotShareholderRecord;
import com.kartaguez.bluepot.utils.Constants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper=false)
public class ExpenseShareholder2 extends VersionedObject2<ExpenseShareholderRecord> {

    private UUID potUuid;
    private UUID potShareholderUuid;
    private UUID expenseUuid;
    private Fraction weight;

    private ExpenseShareholder2(@NonNull PotGlobalVersion _potGlobalVersion, ExpenseShareholderRecord expenseShareholderRecord) {
        super(_potGlobalVersion, expenseShareholderRecord);

        if (null == _potGlobalVersion.getPotUuid()) {
            throw new IllegalArgumentException("PotGlobalVersion PotUuid cannot be null.");
        }

        if (expenseShareholderRecord != null && !_potGlobalVersion.getPotUuid().equals(expenseShareholderRecord.potUuid())) {
            throw new IllegalArgumentException("ExpenseSharedholder PotUuid does not match PotGlobalVersion PotUuid.");
        }
    
        this.potUuid = _potGlobalVersion.getPotUuid();

        if (expenseShareholderRecord != null) {
            this.expenseUuid = expenseShareholderRecord.expenseUuid();
            this.uuid = expenseShareholderRecord.uuid();
            this.deleted = expenseShareholderRecord.deleted();
            this.weight = expenseShareholderRecord.weight();
        }

    }

    public static ExpenseShareholder2 hydrateFromRecord(@NonNull PotGlobalVersion _potGlobalVersion, @NonNull ExpenseShareholderRecord expenseShareholderRecord) {
        return new ExpenseShareholder2(_potGlobalVersion, expenseShareholderRecord);
    }

    public static ExpenseShareholder2 create(@NonNull PotGlobalVersion _potGlobalVersion, @NonNull UUID _potUuid, @NonNull UUID _potShareholderUuid, @NonNull UUID _expenseUuid, @NonNull Fraction _weight) {
        if (Fraction.ZERO.equals(_weight)) {
            throw new IllegalArgumentException("Expense Shareholder weight cannot be 0.");
        }
        ExpenseShareholder2 newExpenseShareholder = new ExpenseShareholder2(_potGlobalVersion, null);
        
        newExpenseShareholder.uuid = UUID.randomUUID();
        newExpenseShareholder.potShareholderUuid = _potShareholderUuid;
        newExpenseShareholder.expenseUuid = _expenseUuid;

        newExpenseShareholder.updateTargetVersionRecord();

        return newExpenseShareholder;
    }

    @Override
    protected ExpenseShareholderRecord createTargetVersionRecord() {
        return new ExpenseShareholderRecord(this.potUuid, this.potShareholderUuid, this.expenseUuid, this.uuid, this.deleted, this.potGlobalVersion.getTargetPotVersion(), Constants.NULL_VERSION, this.weight);
    }

    @Override
    protected void cascadeSetCurrentVersionRecordAsBaseTargetVersionRecord() {
    }

}
