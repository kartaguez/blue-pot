package com.kartaguez.bluepot.crud.domain.bottom.repository;

import java.util.Collection;

import com.kartaguez.bluepot.crud.domain.model.object.PotShareholder;

public interface PotShareholderRepository {

    public Collection<PotShareholder> saveAll(Collection<PotShareholder> shareholders);
}
