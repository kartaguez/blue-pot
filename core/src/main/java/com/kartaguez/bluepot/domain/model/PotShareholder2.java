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
public class PotShareholder2 extends VersionedObject2<PotShareholderRecord> {

    private UUID potUuid;
    private String name;

    private PotShareholder2(@NonNull PotGlobalVersion _potGlobalVersion, PotShareholderRecord potShareholderRecord) {
        if (potShareholderRecord != null && !_potGlobalVersion.getPotUuid().equals(potShareholderRecord.potUuid())) {
            throw new IllegalArgumentException("PotSharedholded PotUuid does not match PotGlobalVersion PotUuid.");
        }
    
        this.potGlobalVersion = _potGlobalVersion;
        this.potUuid = _potGlobalVersion.getPotUuid();

        this.baseVersion = potShareholderRecord;

        if (potShareholderRecord != null) {
            this.uuid = potShareholderRecord.uuid();
            this.deleted = potShareholderRecord.deleted();
            this.name = potShareholderRecord.name();
        }

        this.targetVersion = this.baseVersion;

    }

    public static PotShareholder2 hydrateFromRecord(@NonNull PotGlobalVersion _potGlobalVersion, @NonNull PotShareholderRecord potShareholderRecord) {
        return new PotShareholder2(_potGlobalVersion, potShareholderRecord);
    }

    protected void updateTargetVersion() {
        this.targetVersion = new PotShareholderRecord(this.getPotGlobalVersion().getPotUuid(), this.getUuid(), this.isDeleted(), this.getPotGlobalVersion().getTargetPotVersion(), Constants.NULL_VERSION, this.getName());
    }

    @Override
    protected void cascadeTargetVersionPersisted() {
        return;
    }

    public static PotShareholder2 create(@NonNull PotGlobalVersion _potGlobalVersion, @NonNull String _name) {
        if (Constants.EMPTY_STRING.equals(_name)) {
            throw new IllegalArgumentException("Pot Shareholder name cannot be empty.");
        }
        PotShareholder2 newPotShareholder = new PotShareholder2(_potGlobalVersion, null);
        
        newPotShareholder.uuid = UUID.randomUUID();
        newPotShareholder.name = _name;

        newPotShareholder.updateTargetVersion();

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

        this.updateTargetVersion();

    }

}
