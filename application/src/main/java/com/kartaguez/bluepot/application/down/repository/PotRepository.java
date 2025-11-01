package com.kartaguez.bluepot.application.down.repository;

import java.util.UUID;

import com.kartaguez.bluepot.model.Pot;

public interface PotRepository {

    public void save(Pot pot, Long PotBusinessVersionValue);

    public Pot fetchPotWithUuidAndBusinessVersionValue(UUID potUuid, long potBusinessVersionValue);
}
