package com.kartaguez.bluepot.domain.model;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import com.kartaguez.bluepot.utils.Constants;

import lombok.Builder;
import lombok.Getter;

@Builder(builderMethodName = "internalBuilder")
@Getter
public class Pot {

    private UUID uuid;
    private String name;
    private HashMap<UUID, PotShareholder> potShareholders;

    public static PotBuilder builder(String _name) {
        String trimmedName = checkAndCleanName(_name);
        return internalBuilder().uuid(UUID.randomUUID()).name(trimmedName).potShareholders(new HashMap<UUID, PotShareholder>());
    }

    public void rename(String _newName) {
        String trimmedName = checkAndCleanName(_newName);
        this.name = trimmedName;
    }

    public void addPotShareholder(String potShareholderName) {
        PotShareholder newPotShareholder = PotShareholder.builder(potShareholderName).build();
        if (this.potShareholders.values().stream().anyMatch(potShareholder -> potShareholder.getName().equals(newPotShareholder.getName()))) {
            throw new IllegalArgumentException("PotShareholder with same name already exists");
        };
        this.potShareholders.put(newPotShareholder.getUuid(), newPotShareholder);
    }

    public void addPotShareholders(List<String> potShareholderNames) {
        if (null == potShareholderNames) {
            throw new IllegalArgumentException("PotShareholders list name cannot be null.");
        }
        potShareholderNames.stream().forEach(potShareholderName -> this.addPotShareholder(potShareholderName));
    }

    private static PotBuilder internalBuilder() {
        return new PotBuilder();
    }

    private static String checkAndCleanName(String _newName) {
        if (null == _newName) {
            throw new IllegalArgumentException("Pot name cannot be empty.");
        }
        String trimmedName = _newName.trim();
        if (Constants.EMPTY_STRING.equals(trimmedName)) {
            throw new IllegalArgumentException("Pot name cannot be empty.");
        }
        if (Constants.MAX_LENGTH_POT_NAME < trimmedName.length()) {
            throw new IllegalArgumentException("Pot name is too long ()" + Constants.MAX_LENGTH_POT_NAME + " max).");
        }
        return trimmedName;
    }

}
