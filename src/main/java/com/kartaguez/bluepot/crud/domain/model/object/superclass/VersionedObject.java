package com.kartaguez.bluepot.crud.domain.model.object.superclass;

import java.util.UUID;

import com.kartaguez.bluepot.crud.utils.Constants;

import lombok.Data;

@Data
public class VersionedObject {

    protected UUID uuid;
    protected long version;
    protected long targetGlobalVersion = Constants.FIRST_VERSION;
    protected long createdAtVersion = Constants.FIRST_VERSION;
    protected long deletedAtVersion = Constants.NULL_VERSION;

    public void markAsDeleted() {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Pot already deleted.");
        }
        this.deletedAtVersion = this.targetGlobalVersion;
        this.incrementVersion();
    }

    public boolean isDeleted() {
        return this.deletedAtVersion != Constants.NULL_VERSION;
    }

    protected void incrementVersion() {
        this.version++;
    }

    public static long getPreviousVersion(long currentVersion) {
        return currentVersion - 1;
    }
}
