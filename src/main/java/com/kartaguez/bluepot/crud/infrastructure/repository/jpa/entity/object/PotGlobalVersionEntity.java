package com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity.object;

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
    private Long createdAtVersion;
    private Long deletedAtVersion;

    public PotGlobalVersionEntity(UUID _potUuid, long _createdAtVersion, long _deletedAtVersion) {
        this.potUuid = _potUuid;
        this.createdAtVersion = _createdAtVersion;
        this.deletedAtVersion = _deletedAtVersion;
    }

}
