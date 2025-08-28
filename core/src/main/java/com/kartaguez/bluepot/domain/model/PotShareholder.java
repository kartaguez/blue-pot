package com.kartaguez.bluepot.domain.model;

import java.util.UUID;

import com.kartaguez.bluepot.utils.Constants;

import lombok.Builder;
import lombok.Getter;

@Builder(builderMethodName = "internalBuilder")
@Getter
public class PotShareholder {

    private UUID uuid;
    private String name;

    public static PotShareholderBuilder builder(String _name) {
        String trimmedName = checkAndCleanName(_name);
        return internalBuilder().uuid(UUID.randomUUID()).name(trimmedName);
    }

    private static PotShareholderBuilder internalBuilder() {
        return new PotShareholderBuilder();
    }

    public void rename(String _newName) {
        String trimmedName = checkAndCleanName(_newName);
        this.name = trimmedName;
    }

    private static String checkAndCleanName(String _newName) {
        if (null == _newName) {
            throw new IllegalArgumentException("Pot name cannot be empty.");
        }
        String trimmedName = _newName.trim();
        if (Constants.EMPTY_STRING.equals(trimmedName)) {
            throw new IllegalArgumentException("Pot name cannot be empty.");
        }
        if (Constants.MAX_LENGTH_POTSHAREHOLDER_NAME < trimmedName.length()) {
            throw new IllegalArgumentException("Pot name is too long ()" + Constants.MAX_LENGTH_POTSHAREHOLDER_NAME + " max).");
        }
        return trimmedName;
    }
    
}
