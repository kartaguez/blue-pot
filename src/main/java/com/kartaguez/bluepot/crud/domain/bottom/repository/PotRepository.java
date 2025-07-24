package com.kartaguez.bluepot.crud.domain.bottom.repository;

import java.util.UUID;

import com.kartaguez.bluepot.crud.domain.model.Pot;

public interface PotRepository {

    public Pot loadPotByUuid(UUID potUuid);
}
