package com.kartaguez.bluepot.domain.model;

import java.util.UUID;

import com.kartaguez.bluepot.utils.Constants;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@ToString
public class PotGlobalVersion {
    
    private UUID potUuid;
    private long potVersion;

    private PotGlobalVersion(@NonNull UUID _potUuid, long _potVersion) {
        this.potUuid = _potUuid;
        this.potVersion = _potVersion;
    }

    public static PotGlobalVersion hydrateRoot(@NonNull UUID _potUuid, long _potVersion) {
        return new PotGlobalVersion(_potUuid, _potVersion);
    }

    public static PotGlobalVersion forNewPot(@NonNull UUID _potUuid) {
        return new PotGlobalVersion(_potUuid, Constants.FIRST_VERSION);
    }

    public long incrementVersion() {
        return this.potVersion++;
    }

}
