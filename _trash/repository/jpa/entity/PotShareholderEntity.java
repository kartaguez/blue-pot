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
public class PotShareholderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Version
    private Long version;

    private UUID uuid;
    private UUID potUuid;
    private Long createdAtVersion;
    private Long deletedAtVersion;

    private String name;

    public PotShareholderEntity(UUID _uuid, UUID _potUuid, long _createdAtVersion, long _deletedAtVersion, String _name) {
        this.uuid = _uuid;
        this.potUuid = _potUuid;
        this.createdAtVersion = _createdAtVersion;
        this.deletedAtVersion = _deletedAtVersion;
        this.name = _name;
    }

}
