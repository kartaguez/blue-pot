package com.kartaguez.bluepot.application.down.repository;

import java.util.UUID;

import com.kartaguez.bluepot.domain.model.Pot;

public interface PotRepository {

    public Pot loadPotByUuid(UUID potUuid, long targetGlobalVersion);

    public Pot save(Pot pot);
}
