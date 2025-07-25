package com.kartaguez.bluepot.crud.infrastructure.repository.jpa.entity;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Data

public class PotEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Version
    private Long internalVersion;
    
    private UUID uuid;
    private Long version;

    private Long createdAtVersion;
    private Long deletedAtVersion;
    
    private String name;

    public PotEntity(UUID _uuid, long _version, long _createdAtVersion, long _deletedAtVersion, String _name) {
        this.uuid = _uuid;
        this.version = _version;
        this.createdAtVersion = _createdAtVersion;
        this.deletedAtVersion = _deletedAtVersion;
        this.name = _name;
    }

}
