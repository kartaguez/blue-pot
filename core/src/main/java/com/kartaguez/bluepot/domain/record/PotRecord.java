package com.kartaguez.bluepot.domain.record;

import java.util.UUID;

import lombok.NonNull;

public record PotRecord(@NonNull UUID uuid, boolean deleted, long activeFromVersion, long obsoleteFromVersion, @NonNull String name) {}
