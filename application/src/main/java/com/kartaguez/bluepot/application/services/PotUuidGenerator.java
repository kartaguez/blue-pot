package com.kartaguez.bluepot.application.services;

import java.util.UUID;

public class PotUuidGenerator {

    public UUID getNewUuid() {
        return UUID.randomUUID();
    }

}
