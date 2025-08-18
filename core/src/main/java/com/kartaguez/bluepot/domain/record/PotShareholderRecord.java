package com.kartaguez.bluepot.domain.record;

import java.util.UUID;

import lombok.NonNull;

public record PotShareholderRecord(@NonNull UUID potUuid, @NonNull UUID uuid, boolean deleted, long activeFromVersion, long obsoleteFromVersion, @NonNull String name) {}
