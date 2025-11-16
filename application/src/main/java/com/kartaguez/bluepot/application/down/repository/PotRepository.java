package com.kartaguez.bluepot.application.down.repository;

import java.util.UUID;

import com.kartaguez.bluepot.model.Pot;

public interface PotRepository {

    public Pot fetchPotWithUuidIfBusinessVersionsDoMatch(UUID potUuid, Long expectedPotBusinessVersionValue,
            String expectedPotBusinessVersionStamp);

    public void save(Pot pot, Long expectedPotBusinessVersionValue, String expectedPotBusinessVersionStamp,
            Long potNewBusinessVersionValue, String potNewBusinessVersionStamp);
}
