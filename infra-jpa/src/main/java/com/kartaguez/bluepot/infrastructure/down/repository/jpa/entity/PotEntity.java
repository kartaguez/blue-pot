package com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Version;
import lombok.Data;

@Entity
@Data
@Table(name="pot")
public class PotEntity {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "hibernate_sequence"
    )
    @SequenceGenerator(
        name = "hibernate_sequence",
        allocationSize = 1
    )
    @Column(name = "id")
    private Long id;
    
    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "uuid")
    private UUID uuid;
    @Column(name = "active_from")
    private Long activeFromBusinessVersionValue;
    @Column(name = "inactive_from")
    private Long inactiveFromBusinessVersionValue;
    
    @Column(name = "name")
    private String name;

    protected PotEntity() {}

    public PotEntity(UUID _uuid, Long _activeFromBusinessVersionValue, Long _inactiveFromBusinessVersionValue, String _name) {
        this.uuid = _uuid;
        this.activeFromBusinessVersionValue = _activeFromBusinessVersionValue;
        this.inactiveFromBusinessVersionValue = _inactiveFromBusinessVersionValue;
        this.name = _name;
    }

}
