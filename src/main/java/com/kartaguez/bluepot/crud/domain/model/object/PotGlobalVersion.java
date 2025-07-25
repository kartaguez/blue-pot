package com.kartaguez.bluepot.crud.domain.model.object;

import java.util.UUID;

import com.kartaguez.bluepot.crud.utils.Constants;

import lombok.Data;
import lombok.NonNull;

@Data
public class PotGlobalVersion {

    private UUID potUuid;

    private long value;

    private PotGlobalVersion(@NonNull UUID _potUuid, long _value) {
        this.potUuid = _potUuid;
        this.value = _value;
    }

    public static PotGlobalVersion create(@NonNull UUID _potUuid) {
        return new PotGlobalVersion(_potUuid, Constants.FIRST_VERSION);
    }

    public long incrementVersion() {
        return this.value++;
    }

}
