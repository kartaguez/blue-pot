package com.kartaguez.bluepot.application.down.repository;

import java.util.Collection;
import java.util.UUID;

import com.kartaguez.bluepot.domain.model.PotShareholder_old1;

public interface PotShareholderRepository {

    public PotShareholder_old1 loadPotShareholderByUuid(UUID potShareholderUuid, long targetGlobalVersion);

    public Collection<PotShareholder_old1> saveAll(Collection<PotShareholder_old1> shareholders);
}
