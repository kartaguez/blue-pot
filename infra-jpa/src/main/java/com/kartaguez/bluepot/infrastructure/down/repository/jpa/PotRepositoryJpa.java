package com.kartaguez.bluepot.infrastructure.down.repository.jpa;

import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kartaguez.bluepot.application.down.repository.PotRepository;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity.PotEntity;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.entity.PotGlobalVersionEntity;
import com.kartaguez.bluepot.infrastructure.down.repository.jpa.mapper.PotEntityMapper;
import com.kartaguez.bluepot.model.Pot;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PotRepositoryJpa implements PotRepository {

    private static final Logger log = LoggerFactory.getLogger(PotRepositoryJpa.class);

    private final EntityManager entityManager;
    private final PotEntityMapper potEntityMapper;

    @Override
    public Pot fetchPotWithUuidIfBusinessVersionsDoMatch(UUID potUuid, Long expectedPotBusinessVersionValue,
            String expectedPotBusinessVersionStamp) {
        if (null == potUuid) {
            throw new IllegalArgumentException("potUuid cannot be null.");
        }
        if (null == expectedPotBusinessVersionValue) {
            throw new IllegalArgumentException("expectedPotBusinessVersionValue cannot be null.");
        }
        if (null == expectedPotBusinessVersionStamp) {
            throw new IllegalArgumentException("expectedPotBusinessVersionStamp cannot be null.");
        }

        fetchPotGlobalVersionIfBusinessValueDoesMatch(potUuid, expectedPotBusinessVersionValue, expectedPotBusinessVersionStamp);

        PotEntity potEntity = fetchPotWithBusinessVersionValue(potUuid, expectedPotBusinessVersionValue);
        return potEntityMapper.toDomain(potEntity);
    }

    @Override
    public void save(Pot pot, Long expectedPotBusinessVersionValue, String expectedPotBusinessVersionStamp,
            Long potNewBusinessVersionValue, String potNewBusinessVersionStamp) {

            if (null == pot) {
                throw new IllegalArgumentException("pot cannot be null.");
            }
            UUID potUuid = pot.getUuid();
            if (null == potUuid) {
                throw new IllegalArgumentException("potUuid cannot be null.");
            }
            if (null == expectedPotBusinessVersionValue) {
                throw new IllegalArgumentException("expectedPotBusinessVersionValue cannot be null.");
            }
            if (null == expectedPotBusinessVersionStamp) {
                throw new IllegalArgumentException("expectedPotBusinessVersionStamp cannot be null.");
            }
            if (null == potNewBusinessVersionValue) {
                throw new IllegalArgumentException("potNewBusinessVersionValue cannot be null.");
            }
            if (null == potNewBusinessVersionStamp) {
                throw new IllegalArgumentException("potNewBusinessVersionStamp cannot be null.");
            }

            // 1. Retrieve current PotGlobalVersion against expected V and S
            PotGlobalVersionEntity potGlobalVersionEntity = fetchPotGlobalVersionIfBusinessValueDoesMatch(potUuid, expectedPotBusinessVersionValue, expectedPotBusinessVersionStamp);

            // 2. Create new PotEntity based on updated Pot associated PotEntity
            PotEntity currentVersionPotEntity = fetchPotWithBusinessVersionValue(potUuid, expectedPotBusinessVersionValue);

            // 3. Make current version PotEntity inactive
            currentVersionPotEntity.setInactiveFromBusinessVersionValue(potNewBusinessVersionValue);

            // 5. Create new version PotEntity from updated Pot
            PotEntity newVersionPotEntity = this.potEntityMapper.toEntity(pot, potNewBusinessVersionValue, null);

            // 6. Persist new version PotEntity
            this.entityManager.persist(newVersionPotEntity);

            // 7. Update PotGlobalVersion
            potGlobalVersionEntity.setPotBusinessVersionValue(potNewBusinessVersionValue);
            potGlobalVersionEntity.setPotBusinessVersionStamp(potNewBusinessVersionStamp);

    }

    private PotGlobalVersionEntity fetchPotGlobalVersionIfBusinessValueDoesMatch(UUID potUuid, Long expectedPotBusinessVersionValue,
            String expectedPotBusinessVersionStamp) {
        CriteriaBuilder fetchPotGlobalVersionCriteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PotGlobalVersionEntity> fetchPotGlobalVersionCriteriaQuery = fetchPotGlobalVersionCriteriaBuilder.createQuery(PotGlobalVersionEntity.class);
        Root<PotGlobalVersionEntity> potGlobalVersionEntityRoot = fetchPotGlobalVersionCriteriaQuery.from(PotGlobalVersionEntity.class);
        
        Predicate predicatePotGlobalVersionUuid = fetchPotGlobalVersionCriteriaBuilder.equal(potGlobalVersionEntityRoot.get("potUuid"), potUuid);
        Predicate predicatePotGlobalVersionBusinessVersionValue = fetchPotGlobalVersionCriteriaBuilder.equal(potGlobalVersionEntityRoot.get("potBusinessVersionValue"), expectedPotBusinessVersionValue);
        Predicate predicatePotGlobalVersionBusinessVersionStamp = fetchPotGlobalVersionCriteriaBuilder.equal(potGlobalVersionEntityRoot.get("potBusinessVersionStamp"), expectedPotBusinessVersionStamp);
        Predicate predicatePotGlobalVersionUuidAndVersion =  fetchPotGlobalVersionCriteriaBuilder.and(predicatePotGlobalVersionUuid, predicatePotGlobalVersionBusinessVersionValue, predicatePotGlobalVersionBusinessVersionStamp);
        fetchPotGlobalVersionCriteriaQuery.where(predicatePotGlobalVersionUuidAndVersion);

        List<PotGlobalVersionEntity> potGlobalVersionEntities = this.entityManager.createQuery(fetchPotGlobalVersionCriteriaQuery).getResultList();

        log.info("Nb of PotGlobalVersion entities found: " + potGlobalVersionEntities.size());
         if (potGlobalVersionEntities.size() > 1) {
            throw new IllegalStateException ("Zero or one PotGlobalVersionEntity expected, but " +  potGlobalVersionEntities.size() + " found.");
         }
         if (potGlobalVersionEntities.size() == 0) {
            throw new IllegalArgumentException ("PotGlobalVersionEntity found matching expectedBusinessVersion Value or Stamp");
         }

        log.info("PotGlobalVersion found: potUuid:" + potGlobalVersionEntities.getFirst().getPotUuid() + ", businessVersionValue: " + potGlobalVersionEntities.getFirst().getPotBusinessVersionValue() + ", businessVersionStamp: " + potGlobalVersionEntities.getFirst().getPotBusinessVersionStamp());
        return potGlobalVersionEntities.getFirst();
    }

    private PotEntity fetchPotWithBusinessVersionValue(UUID potUuid, Long expectedPotBusinessVersionValue) {
        CriteriaBuilder fetchPotCriteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<PotEntity> fetchPotCriteriaQuery = fetchPotCriteriaBuilder.createQuery(PotEntity.class);
        Root<PotEntity> potEntityRoot = fetchPotCriteriaQuery.from(PotEntity.class);
        
        Predicate predicatePotUuid = fetchPotCriteriaBuilder.equal(potEntityRoot.get("uuid"), potUuid);
        Predicate predicateActivePotBusinessVersionValue = fetchPotCriteriaBuilder.lessThanOrEqualTo(potEntityRoot.get("activeFromBusinessVersionValue"), expectedPotBusinessVersionValue);
        Predicate predicateInactivePotBusinessVersionValueNull = fetchPotCriteriaBuilder.isNull(potEntityRoot.get("inactiveFromBusinessVersionValue"));
        Predicate predicateInactivePotBusinessVersionValueUpperBound = fetchPotCriteriaBuilder.greaterThan(potEntityRoot.get("inactiveFromBusinessVersionValue"), expectedPotBusinessVersionValue);
        Predicate predicateInactivePotBusinessVersionValue = fetchPotCriteriaBuilder.or(predicateInactivePotBusinessVersionValueNull, predicateInactivePotBusinessVersionValueUpperBound);
        Predicate predicatePotUuidAndVersion =  fetchPotCriteriaBuilder.and(predicatePotUuid, predicateActivePotBusinessVersionValue, predicateInactivePotBusinessVersionValue);
        fetchPotCriteriaQuery.where(predicatePotUuidAndVersion);

        List<PotEntity> potEntities = this.entityManager.createQuery(fetchPotCriteriaQuery).getResultList();
        log.info("Nb of Pot entities found: " + potEntities.size());

        if (potEntities.size() != 1) {
            throw new IllegalStateException ("One PotEntity expected, but " +  potEntities.size() + " found.");
        }

        log.info("Pot found: potUuid:" + potEntities.getFirst().getUuid() + ", active from version: " + potEntities.getFirst().getActiveFromBusinessVersionValue() + ", inactive from version: " + potEntities.getFirst().getInactiveFromBusinessVersionValue());
        return potEntities.getFirst();
    }

}
