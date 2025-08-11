package com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Data
public class PotGlobalVersionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Version
    private Long version;

    private UUID potUuid;
    private Long potVersion;

    public PotGlobalVersionEntity(Long _id, UUID _potUuid, long _potVersion) {
        this.id = _id;
        this.potUuid = _potUuid;
        this.potVersion = _potVersion;
    }

}
