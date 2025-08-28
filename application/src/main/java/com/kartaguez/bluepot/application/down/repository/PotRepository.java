package com.kartaguez.bluepot.application.down.repository;

import java.util.UUID;

import com.kartaguez.bluepot.domain._to_delete.model.Pot_old1;

public interface PotRepository {

    public Pot_old1 loadPotByUuid(UUID potUuid, long targetGlobalVersion);

    public Pot_old1 save(Pot_old1 pot);
}
