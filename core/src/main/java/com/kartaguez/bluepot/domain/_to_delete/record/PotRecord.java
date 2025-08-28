package com.kartaguez.bluepot.domain._to_delete.record;

import java.util.UUID;

import lombok.NonNull;

public record PotRecord(@NonNull UUID uuid, boolean deleted, long activeFromVersion, long obsoleteFromVersion, @NonNull String name) {}
