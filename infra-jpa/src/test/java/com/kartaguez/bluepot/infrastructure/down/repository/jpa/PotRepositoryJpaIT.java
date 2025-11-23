package com.kartaguez.bluepot.infrastructure.down.repository.jpa;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.kartaguez.bluepot.infrastructure.down.repository.jpa.mapper.PotEntityMapper;
import com.kartaguez.bluepot.model.Pot;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class PotRepositoryJpaIT {

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
    void should_load_preexisting_pot() {
        Pot pot = this.potRepositoryJpa.fetchPotWithUuidIfBusinessVersionsDoMatch(UUID.fromString("40adf588-581c-450f-88b3-b2f399a32632"), Long.valueOf(2), "bbbb");
        assertEquals("Pot Ardèche", pot.getName());
    }
}
