package com.kartaguez.bluepot.infrastructure.up.dummy.config.application;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.application.down.repository.ExpenseRepository;
import com.kartaguez.bluepot.application.down.repository.ExpenseShareholderRepository;
import com.kartaguez.bluepot.application.down.repository.PotGlobalVersionRepository;
import com.kartaguez.bluepot.application.down.repository.PotRepository;
import com.kartaguez.bluepot.application.down.repository.PotShareholderRepository;
import com.kartaguez.bluepot.application.usecase.CreatePotUseCase;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Component
@Configuration
@Data
@RequiredArgsConstructor
public class UseCaseConfig {

    private final PotGlobalVersionRepository potGlobalVersionRepository;
    private final PotRepository potRepository;
    private final PotShareholderRepository potShareholderRepository;
    private final ExpenseRepository expenseRepository;
    private final ExpenseShareholderRepository expenseShareholderRepository;

    @Bean
    public CreatePotUseCase createPotUseCase() {
        return new CreatePotUseCase(this.potGlobalVersionRepository, this.potRepository, this.potShareholderRepository);
    }

}
