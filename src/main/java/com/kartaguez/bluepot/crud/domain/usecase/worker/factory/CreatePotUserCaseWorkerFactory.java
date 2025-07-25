package com.kartaguez.bluepot.crud.domain.usecase.worker.factory;

import org.springframework.stereotype.Component;

import com.kartaguez.bluepot.crud.domain.bottom.repository.PotGlobalVersionRepository;
import com.kartaguez.bluepot.crud.domain.bottom.repository.PotRepository;
import com.kartaguez.bluepot.crud.domain.bottom.repository.PotShareholderRepository;
import com.kartaguez.bluepot.crud.domain.usecase.mapper.CreatePotDtoInMapper;
import com.kartaguez.bluepot.crud.domain.usecase.mapper.CreatePotDtoOutMapper;
import com.kartaguez.bluepot.crud.domain.usecase.worker.CreatePotUseCaseWorker;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CreatePotUserCaseWorkerFactory {

    private final PotGlobalVersionRepository potGlobalversionRepository;
    private final PotRepository potRepository;
    private final PotShareholderRepository potShareholderRepository;
    private final CreatePotDtoInMapper createPotDtoInMapper;
    private final CreatePotDtoOutMapper createPotDtoOutMapper;

    public CreatePotUseCaseWorker getNewWorker() {
        return CreatePotUseCaseWorker.getNewInstance(this.potGlobalversionRepository, this.potRepository, this.potShareholderRepository, this.createPotDtoInMapper, this.createPotDtoOutMapper);
    }
}
