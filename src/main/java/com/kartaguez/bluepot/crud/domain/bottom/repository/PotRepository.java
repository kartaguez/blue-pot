package com.kartaguez.bluepot.crud.domain.bottom.repository;

import java.util.UUID;

import com.kartaguez.bluepot.crud.domain.model.object.Pot;

public interface PotRepository {

    public Pot loadPotByUuid(UUID potUuid, long targetGlobalVersion);

    public Pot save(Pot pot);
}
