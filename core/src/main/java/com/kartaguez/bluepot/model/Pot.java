package com.kartaguez.bluepot.model;

import java.util.HashMap;
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

    public static PotBuilder builder(UUID _uuid, String _name) {
        if (null == _uuid) {
            throw new IllegalArgumentException("Pot Uuid cannot be null.");
        }
        String trimmedName = checkAndCleanName(_name);
        return internalBuilder().uuid(_uuid).name(trimmedName).potShareholders(new HashMap<UUID, PotShareholder>());
    }

    public void rename(String _newName) {
        String trimmedName = checkAndCleanName(_newName);
        this.name = trimmedName;
    }

    public void addPotShareholder(UUID potShareholderUuid, String potShareholderName) {
        if (null == potShareholderUuid) {
            throw new IllegalArgumentException("PotShareholder Uuid cannot be null.");
        }
        if (this.potShareholders.containsKey(potShareholderUuid)) {
            throw new IllegalArgumentException("PotShareholder with same Uuid already exists");
        }
        PotShareholder newPotShareholder = PotShareholder.builder(potShareholderUuid, this.uuid, potShareholderName).build();
        // FOR REUSE
        // if (this.potShareholders.values().stream().anyMatch(potShareholder -> potShareholder.getName().equals(newPotShareholder.getName()))) {
        //     throw new IllegalArgumentException("PotShareholder with same name already exists");
        // };
        this.potShareholders.put(potShareholderUuid, newPotShareholder);
    }

    public void addPotShareholders(HashMap<UUID, String> potShareholderNames) {
        if (null == potShareholderNames) {
            throw new IllegalArgumentException("PotShareholders list name cannot be null.");
        }
        potShareholderNames.keySet().stream().forEach(potShareholderUuid -> this.addPotShareholder(potShareholderUuid, potShareholderNames.get(potShareholderUuid)));
    }

    private static PotBuilder internalBuilder() {
        return new PotBuilder();
    }

    private static String checkAndCleanName(String _newName) {
        if (null == _newName) {
            throw new IllegalArgumentException("PotShareholder name cannot be empty.");
        }
        String trimmedName = _newName.trim();
        if (Constants.EMPTY_STRING.equals(trimmedName)) {
            throw new IllegalArgumentException("PotShareholder name cannot be empty.");
        }
        if (Constants.MAX_LENGTH_POT_NAME < trimmedName.length()) {
            throw new IllegalArgumentException("PotShareholder name is too long ()" + Constants.MAX_LENGTH_POT_NAME + " max).");
        }
        return trimmedName;
    }

    public void renamePotShareholder(UUID potShareholderUUid, String newPotShareholderName) {
        if (null == potShareholderUUid) {
            throw new IllegalArgumentException("PotShareholder Uuid cannot be null.");
        }
        if (null == this.potShareholders.get(potShareholderUUid)) {
            throw new IllegalArgumentException("Uuid does not belong to PotShareholder's Uuids.");
        }
        this.potShareholders.get(potShareholderUUid).rename(newPotShareholderName);
    }

}
