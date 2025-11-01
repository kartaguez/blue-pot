package com.kartaguez.bluepot.application.down.repository;

import java.util.UUID;

import com.kartaguez.bluepot.application.persistence.PotGlobalVersion;

public interface PotGlobalVersionRepository {

    public void save(UUID potUuid, long PotBusinessVersionValue, String PotBusinessVersionStamp);

    public boolean expectedAndCurrentBusinessVersionValueAndStampDoMatch(UUID potUuid, long PotBusinessVersionValue, String PotBusinessVersionStamp);

    public PotGlobalVersion fetchPotWithUuidIfCurrentAndExpectedBusinessVersionValueAndStampDoMatch(UUID potUuid, long PotBusinessVersionValue, String PotBusinessVersionStamp);
}
