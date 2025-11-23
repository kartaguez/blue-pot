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
@Table(name="pot_global_version")
public class PotGlobalVersionEntity {

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

    @Column(name = "pot_uuid")
    private UUID potUuid;
    @Column(name = "pot_business_version_value")
    private long potBusinessVersionValue;
@Column(name = "pot_business_version_stamp")
    private String potBusinessVersionStamp;

    protected PotGlobalVersionEntity() {}

    public PotGlobalVersionEntity(Long _id, UUID _potUuid, Long _potBusinessVersionValue, String _potBusinessVersionStamp) {
        this.id = _id;
        this.potUuid = _potUuid;
        this.potBusinessVersionValue = _potBusinessVersionValue;
        this.potBusinessVersionStamp = _potBusinessVersionStamp;
    }

}
