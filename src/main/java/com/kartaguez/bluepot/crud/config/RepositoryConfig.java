package com.kartaguez.bluepot.crud.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kartaguez.bluepot.crud.domain.bottom.repository.ExpenseRepository;
import com.kartaguez.bluepot.crud.domain.bottom.repository.ExpenseShareholderRepository;
import com.kartaguez.bluepot.crud.domain.bottom.repository.PotGlobalVersionRepository;
import com.kartaguez.bluepot.crud.domain.bottom.repository.PotRepository;
import com.kartaguez.bluepot.crud.domain.bottom.repository.PotShareholderRepository;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.ExpenseRepositoryJpa;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.ExpenseShareholderRepositoryJpa;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.PotGlobalVersionRepositoryJpa;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.PotRepositoryJpa;
import com.kartaguez.bluepot.crud.infrastructure.repository.jpa.PotShareholderRepositoryJpa;

@Configuration
public class RepositoryConfig {

    @Bean
    public PotGlobalVersionRepository potGlobalVersionRepository() {
        return new PotGlobalVersionRepositoryJpa();
    }

    @Bean
    public PotRepository potRepository() {
        return new PotRepositoryJpa();
    }

    @Bean
    public PotShareholderRepository potShareholderRepository() {
        return new PotShareholderRepositoryJpa();
    }

    @Bean
    public ExpenseRepository expenseRepository() {
        return new ExpenseRepositoryJpa();
    }
    
    @Bean
    public ExpenseShareholderRepository expenseShareholderRepository() {
        return new ExpenseShareholderRepositoryJpa();
    }
}
