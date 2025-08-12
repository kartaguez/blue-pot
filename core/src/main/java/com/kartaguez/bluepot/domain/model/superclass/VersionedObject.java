package com.kartaguez.bluepot.domain.model.superclass;

import java.util.UUID;

import com.kartaguez.bluepot.utils.Constants;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class VersionedObject {

    protected UUID uuid;
    protected long currentGlobalVersion = Constants.FIRST_VERSION;
    protected long createdAtVersion = Constants.FIRST_VERSION;
    protected long deletedAtVersion = Constants.NULL_VERSION;

    public long getNextGlobalVersion() {
        return this.currentGlobalVersion + 1;
    }

    public void incrementCurrentGlobalVersion() {
        this.currentGlobalVersion++;
    }

    public void markAsDeleted() {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Object already deleted.");
        }
        this.deletedAtVersion = this.currentGlobalVersion;
    }

    public boolean isDeleted() {
        return this.deletedAtVersion != Constants.NULL_VERSION;
    }

    public static long getPreviousVersion(long currentVersion) {
        return currentVersion - 1;
    }
}
