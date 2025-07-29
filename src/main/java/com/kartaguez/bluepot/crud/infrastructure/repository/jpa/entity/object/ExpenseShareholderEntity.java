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
public class ExpenseShareholderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Version
    private Long version;

    private UUID uuid;
    private Long createdAtVersion;
    private Long deletedAtVersion;
    
    private UUID expenseUuid;
    private UUID potShareholderUuid;
    private String weight;

    public ExpenseShareholderEntity(UUID _uuid, long _createdAtVersion, long _deletedAtVersion, UUID _expenseUuid, UUID _potShareholderUuid, String _weight) {
        this.uuid = _uuid;
        this.createdAtVersion = _createdAtVersion;
        this.deletedAtVersion = _deletedAtVersion;
        this.expenseUuid = _expenseUuid;
        this.potShareholderUuid = _potShareholderUuid;
        this.weight = _weight;
    }

}
