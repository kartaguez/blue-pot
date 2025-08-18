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
    private long currentPotVersion;
    private long targetPotVersion;

    private PotGlobalVersion(@NonNull UUID _potUuid, long _currentPotVersion) {
        this.potUuid = _potUuid;
        this.currentPotVersion = _currentPotVersion;
        updateTargetPotVersion();
    }

    private void updateTargetPotVersion() {
        this.targetPotVersion = getNextGlobalVersion();
    }

    public long getNextGlobalVersion() {
        return this.currentPotVersion + 1;
    }

    public long getPreviousGlobalVersion() {
        return this.currentPotVersion - 1;
    }

    public void markTargetVersionAsPersisted() {
        this.currentPotVersion = this.targetPotVersion;
        updateTargetPotVersion();
    }

    public static PotGlobalVersion hydrateRoot(@NonNull UUID _potUuid, long _potVersion) {
        return new PotGlobalVersion(_potUuid, _potVersion);
    }

    public static PotGlobalVersion forNewPot(@NonNull UUID _potUuid) {
        return new PotGlobalVersion(_potUuid, Constants.BEFORE_FIRST_VERSION);
    }

}
