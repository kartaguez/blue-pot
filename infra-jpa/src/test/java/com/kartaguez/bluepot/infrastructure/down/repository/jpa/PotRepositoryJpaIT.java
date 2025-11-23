package com.kartaguez.bluepot.infrastructure.down.repository.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kartaguez.bluepot.infrastructure.down.repository.jpa.mapper.PotEntityMapper;
import com.kartaguez.bluepot.model.Pot;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PotRepositoryJpaIT {

    private static final Logger log = LoggerFactory.getLogger(PotRepositoryJpaIT.class);

    private static EntityManagerFactory emf;
    private EntityManager em;
    private PotRepositoryJpa potRepositoryJpa;

    @BeforeAll
    static void initEmf() {
        PotRepositoryJpaIT.emf = Persistence.createEntityManagerFactory("pu_infra-jpa_IT");
    }

    @BeforeEach
    void init() {
        this.em = PotRepositoryJpaIT.emf.createEntityManager();
        this.em.getTransaction().begin();
        this.potRepositoryJpa = new PotRepositoryJpa(em, new PotEntityMapper());
    }

    @AfterEach
    void tearDown() {
        if (this.em.getTransaction().isActive()) {
            this.em.getTransaction().rollback();
        }
        em.close();
    }

    @AfterAll
    static void closeEmf() {
        PotRepositoryJpaIT.emf.close();
    }

    @Test
    void load_preexisting_pot_and_rename_it() {
        log.info("2: load_preexisting_pot_and_rename_it");
        Long expectedPotBusinessVersionValue = Long.valueOf(2L);
        String expectedPotBusinessVersionStamp = "bbbb";
        String expectedPotName = "Pot Ardèche";
        String newPotName = "Pot Ardèche 2";
        Pot pot = this.potRepositoryJpa.fetchPotWithUuidIfBusinessVersionsDoMatch(UUID.fromString("40adf588-581c-450f-88b3-b2f399a32632"), expectedPotBusinessVersionValue, expectedPotBusinessVersionStamp);
        assertEquals(expectedPotName, pot.getName());
        
        pot.rename(newPotName);

        Long newPotBusinessVersionValue = Long.valueOf(3);
        String newPotBusinessVersionStamp = "cccc";
        this.potRepositoryJpa.save(pot, expectedPotBusinessVersionValue, expectedPotBusinessVersionStamp, newPotBusinessVersionValue, newPotBusinessVersionStamp);

        this.em.getTransaction().commit();
    
        expectedPotBusinessVersionValue = Long.valueOf(3L);
        expectedPotBusinessVersionStamp = "cccc";
        expectedPotName = "Pot Ardèche 2";
        pot = this.potRepositoryJpa.fetchPotWithUuidIfBusinessVersionsDoMatch(UUID.fromString("40adf588-581c-450f-88b3-b2f399a32632"), expectedPotBusinessVersionValue, expectedPotBusinessVersionStamp);
        assertEquals(expectedPotName, pot.getName());
    }

}
