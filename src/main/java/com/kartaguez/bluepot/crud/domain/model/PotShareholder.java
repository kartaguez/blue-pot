package com.kartaguez.bluepot.crud.domain.model;

import java.util.UUID;

import lombok.Data;

@Data
public class PotShareholder {

    private UUID uuid;
    private long id;
    
    private UUID potUuid;

    private String name;

    public PotShareholder(UUID _potUuid, String _name) {
        this.potUuid = _potUuid;
        this.name = _name;
    }
}
