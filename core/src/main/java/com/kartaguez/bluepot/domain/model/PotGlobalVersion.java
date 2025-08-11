package com.kartaguez.bluepot.domain.model;

import java.util.UUID;

import com.kartaguez.bluepot.utils.Constants;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class PotGlobalVersion {

    private Long id;
    
    private UUID potUuid;
    private long potVersion;

    private PotGlobalVersion(Long _id, @NonNull UUID _potUuid, long _potVersion) {
        this.id = _id;
        this.potUuid = _potUuid;
        this.potVersion = _potVersion;
    }

    public static PotGlobalVersion hydrateRoot(@NonNull Long _id, @NonNull UUID _potUuid, long _potVersion) {
        return new PotGlobalVersion(_id, _potUuid, _potVersion);
    }

    public static PotGlobalVersion create(@NonNull UUID _potUuid) {
        return new PotGlobalVersion(null, _potUuid, Constants.FIRST_VERSION);
    }

    public long incrementVersion() {
        return this.potVersion++;
    }

}
