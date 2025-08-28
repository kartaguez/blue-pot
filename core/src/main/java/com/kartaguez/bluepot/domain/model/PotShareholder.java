package com.kartaguez.bluepot.domain.model;

import java.util.UUID;

import com.kartaguez.bluepot.utils.Constants;

import lombok.Builder;
import lombok.Getter;

@Builder(builderMethodName = "internalBuilder")
@Getter
public class PotShareholder {

    private UUID potUuid;
    private UUID uuid;
    private String name;

    public static PotShareholderBuilder builder(UUID _potUuid, String _name) {
        if (null == _potUuid) {
            throw new IllegalArgumentException("Pot Uuid name cannot be null.");
        }
        if (null == _name) {
            throw new IllegalArgumentException("PotShareholder name cannot be empty.");
        }
        String trimmedName = _name.trim();
        if (Constants.EMPTY_STRING.equals(trimmedName)) {
            throw new IllegalArgumentException("PotShareholder name cannot be empty.");
        }
        if (Constants.MAX_LENGTH_POTSHAREHOLDER_NAME < trimmedName.length()) {
            throw new IllegalArgumentException("Pot name is too long ()" + Constants.MAX_LENGTH_POT_NAME + " max).");
        }
        return internalBuilder().uuid(UUID.randomUUID()).potUuid(_potUuid).name(trimmedName);
    }

    private static PotShareholderBuilder internalBuilder() {
        return new PotShareholderBuilder();
    }
    
}
