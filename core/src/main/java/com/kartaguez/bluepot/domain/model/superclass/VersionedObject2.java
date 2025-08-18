package com.kartaguez.bluepot.domain.model.superclass;

import java.util.UUID;

import com.kartaguez.bluepot.domain.model.PotGlobalVersion;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public abstract class VersionedObject2<T extends Record> {

    protected PotGlobalVersion potGlobalVersion;
    protected UUID uuid;
    protected boolean deleted;
    protected T baseVersion;
    protected T targetVersion;

    public void markAsDeleted() {
        if (this.isDeleted()) {
            throw new java.lang.IllegalStateException("Object already deleted.");
        }
        this.deleted = true;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    protected abstract void updateTargetVersion();

    public void recordTargetVersionPersisted() {
        this.potGlobalVersion.markTargetVersionAsPersisted();
        markTargetVersionAsPersisted();
    }

    public void markTargetVersionAsPersisted() {
        this.baseVersion = this.targetVersion;
        cascadeTargetVersionPersisted();
    }

    protected abstract void cascadeTargetVersionPersisted();

}
