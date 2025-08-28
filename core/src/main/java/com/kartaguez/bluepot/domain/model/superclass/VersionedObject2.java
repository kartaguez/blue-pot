package com.kartaguez.bluepot.domain.model.superclass;

import java.util.HashMap;
import java.util.UUID;

import com.kartaguez.bluepot.domain.model.PotGlobalVersion;

import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public abstract class VersionedObject2<T extends Record> {

    protected PotGlobalVersion potGlobalVersion;
    protected UUID uuid;
    protected boolean deleted;
    protected HashMap<Long, T> versionRecords;

    protected VersionedObject2(@NonNull PotGlobalVersion _potGlobalVersion, T baseRecord) {

        this.potGlobalVersion = _potGlobalVersion;
        this.versionRecords = new HashMap<Long, T>();
        this.versionRecords.put(this.potGlobalVersion.getCurrentPotVersion(), baseRecord);

    }

    public void markAsDeleted() {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Object already deleted.");
        }
        this.deleted = true;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    protected void updateTargetVersionRecord() {
        T targetVersionRecord = this.createTargetVersionRecord();
        versionRecords.put(this.potGlobalVersion.getTargetPotVersion(), targetVersionRecord);
    }

    protected abstract T createTargetVersionRecord();

    public void incrementTargetVersion() {
        this.potGlobalVersion.incrementTargetVersion();
        setCurrentVersionRecordAsBaseTargetVersionRecord();
    }

    public void setCurrentVersionRecordAsBaseTargetVersionRecord() {
        versionRecords.put(this.potGlobalVersion.getTargetPotVersion(), versionRecords.get(this.potGlobalVersion.getCurrentPotVersion()));
        cascadeSetCurrentVersionRecordAsBaseTargetVersionRecord();
    }

    protected abstract void cascadeSetCurrentVersionRecordAsBaseTargetVersionRecord();

}
