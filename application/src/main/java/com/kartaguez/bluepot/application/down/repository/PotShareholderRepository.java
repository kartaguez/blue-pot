package com.kartaguez.bluepot.application.down.repository;

import java.util.Collection;
import java.util.UUID;

import com.kartaguez.bluepot.domain.model.PotShareholder;

public interface PotShareholderRepository {

    public PotShareholder loadPotShareholderByUuid(UUID potShareholderUuid, long targetGlobalVersion);

    public Collection<PotShareholder> saveAll(Collection<PotShareholder> shareholders);
}
