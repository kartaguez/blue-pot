package com.kartaguez.bluepot.domain.model;

import java.util.UUID;

import com.kartaguez.bluepot.utils.Constants;

import lombok.Builder;
import lombok.Getter;

@Builder(builderMethodName = "internalBuilder")
@Getter
public class Pot {

    private UUID uuid;
    private String name;

    public static PotBuilder builder(String _name) {
        if (null == _name) {
            throw new IllegalArgumentException("Pot name cannot be empty.");
        }
        String trimmedName = _name.trim();
        if (Constants.EMPTY_STRING.equals(trimmedName)) {
            throw new IllegalArgumentException("Pot name cannot be empty.");
        }
        if (Constants.MAX_LENGTH_POT_NAME < trimmedName.length()) {
            throw new IllegalArgumentException("Pot name is too long ()" + Constants.MAX_LENGTH_POT_NAME + " max).");
        }
        return internalBuilder().uuid(UUID.randomUUID()).name(trimmedName);
    }

    private static PotBuilder internalBuilder() {
        return new PotBuilder();
    }

}
