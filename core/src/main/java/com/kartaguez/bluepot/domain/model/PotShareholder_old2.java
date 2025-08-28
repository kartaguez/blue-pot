package com.kartaguez.bluepot.domain.model;

import java.util.UUID;

import com.kartaguez.bluepot.domain.model.superclass.VersionedObject2;
import com.kartaguez.bluepot.domain.record.PotShareholderRecord;
import com.kartaguez.bluepot.utils.Constants;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;

@Getter
@EqualsAndHashCode(callSuper=false)
public class PotShareholder_old2 extends VersionedObject2<PotShareholderRecord> {

    private UUID potUuid;
    private String name;

    private PotShareholder_old2(@NonNull PotGlobalVersion _potGlobalVersion, PotShareholderRecord potShareholderRecord) {
        super(_potGlobalVersion, potShareholderRecord);

        if (null == _potGlobalVersion.getPotUuid()) {
            throw new IllegalArgumentException("PotGlobalVersion PotUuid cannot be null.");
        }

        if (potShareholderRecord != null && !_potGlobalVersion.getPotUuid().equals(potShareholderRecord.potUuid())) {
            throw new IllegalArgumentException("PotSharedholded PotUuid does not match PotGlobalVersion PotUuid.");
        }
    
        this.potUuid = _potGlobalVersion.getPotUuid();

        if (potShareholderRecord != null) {
            this.uuid = potShareholderRecord.uuid();
            this.deleted = potShareholderRecord.deleted();
            this.name = potShareholderRecord.name();
        }

    }

    public static PotShareholder_old2 hydrateFromRecord(@NonNull PotGlobalVersion _potGlobalVersion, @NonNull PotShareholderRecord potShareholderRecord) {
        return new PotShareholder_old2(_potGlobalVersion, potShareholderRecord);
    }

    public static PotShareholder_old2 create(@NonNull PotGlobalVersion _potGlobalVersion, @NonNull String _name) {
        if (Constants.EMPTY_STRING.equals(_name)) {
            throw new IllegalArgumentException("Pot Shareholder name cannot be empty.");
        }
        PotShareholder_old2 newPotShareholder = new PotShareholder_old2(_potGlobalVersion, null);
        
        newPotShareholder.uuid = UUID.randomUUID();
        newPotShareholder.rename(_name);

        return newPotShareholder;
    }

    public void rename(@NonNull String _name) {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("PotShareholder deleted.");
        }
        if (Constants.EMPTY_STRING.equals(_name)) {
            throw new IllegalArgumentException("PotShareholder name cannot be empty.");
        }
        
        this.name = _name;

        this.updateTargetVersionRecord();

    }

    @Override
    protected PotShareholderRecord createTargetVersionRecord() {
        return new PotShareholderRecord(this.potUuid, this.uuid, this.deleted, this.potGlobalVersion.getTargetPotVersion(), Constants.NULL_VERSION, this.name);
    }

    @Override
    protected void cascadeSetCurrentVersionRecordAsBaseTargetVersionRecord() {
    }

}
