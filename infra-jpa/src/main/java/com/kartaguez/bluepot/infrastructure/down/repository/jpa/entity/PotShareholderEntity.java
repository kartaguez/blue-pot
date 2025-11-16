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
    private Long activeFromBusinessVersionValue;
    private Long inactiveFromBusinessVersionValue;

    private String name;

    public PotShareholderEntity(UUID _uuid, UUID _potUuid, Long _activeFromBusinessVersionValue, Long _inactiveFromBusinessVersionValue, String _name) {
        this.uuid = _uuid;
        this.potUuid = _potUuid;
        this.activeFromBusinessVersionValue = _activeFromBusinessVersionValue;
        this.inactiveFromBusinessVersionValue = _inactiveFromBusinessVersionValue;
        this.name = _name;
    }

}
