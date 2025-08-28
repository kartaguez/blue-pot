package com.kartaguez.bluepot.domain._to_delete.record;

import java.util.UUID;

import org.apache.commons.lang3.math.Fraction;

import lombok.NonNull;

public record ExpenseShareholderRecord(@NonNull UUID potUuid, @NonNull UUID potShareholderUuid, @NonNull UUID expenseUuid, @NonNull UUID uuid, boolean deleted, long activeFromVersion, long obsoleteFromVersion, @NonNull Fraction weight) {}
