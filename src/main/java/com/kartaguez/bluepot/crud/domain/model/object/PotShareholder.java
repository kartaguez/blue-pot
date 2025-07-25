package com.kartaguez.bluepot.crud.domain.model.object;

import java.util.UUID;

import com.kartaguez.bluepot.crud.domain.model.mutation.PotShareholderMutationResultSet;
import com.kartaguez.bluepot.crud.domain.model.object.superclass.VersionedObject;
import com.kartaguez.bluepot.crud.utils.Constants;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(callSuper=false)
public class PotShareholder extends VersionedObject {

    private UUID potUuid;
    
    private String name;

    public PotShareholder(@NonNull UUID _uuid, @NonNull UUID _potUuid, long _targetGlobalVersion, long _createdAtVersion, long _deletedAtVersion, @NonNull String _name) {
        this.uuid = _uuid;
    
        this.potUuid = _potUuid;
        this.targetGlobalVersion = _targetGlobalVersion;
        this.createdAtVersion = _createdAtVersion;
        this.deletedAtVersion = _deletedAtVersion;

        this.name = _name;
    }

    public static PotShareholderMutationResultSet create(@NonNull UUID _potUuid, long _targetGlobalVersion, @NonNull String _name) {
        if (Constants.EMPTY_STRING.equals(_name)) {
            throw new IllegalArgumentException("Pot Shareholder name cannot be empty.");
        }
        return new PotShareholderMutationResultSet(null, new PotShareholder(UUID.randomUUID(), _potUuid, _targetGlobalVersion, _targetGlobalVersion, Constants.NULL_VERSION, _name));
    }

    public PotShareholderMutationResultSet rename(@NonNull String _name) {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("PotShareholder deleted.");
        }
        if (Constants.EMPTY_STRING.equals(_name)) {
            throw new IllegalArgumentException("PotShareholder name cannot be empty.");
        }
        
        PotShareholder renamedPotShareholder = new PotShareholder(this.uuid, this.potUuid, this.targetGlobalVersion, this.targetGlobalVersion, Constants.NULL_VERSION, _name);
        this.markAsDeleted();
        
        return new PotShareholderMutationResultSet(this, renamedPotShareholder);
    }

}
