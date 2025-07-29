package com.kartaguez.bluepot.crud.domain.bottom.repository;

import java.util.Collection;
import java.util.UUID;

import com.kartaguez.bluepot.crud.domain.model.object.PotShareholder;

public interface PotShareholderRepository {

    public PotShareholder loadPotShareholderByUuid(UUID potShareholderUuid, long targetGlobalVersion);

    public Collection<PotShareholder> saveAll(Collection<PotShareholder> shareholders);
}
