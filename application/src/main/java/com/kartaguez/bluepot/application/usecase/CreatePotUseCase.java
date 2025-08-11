package com.kartaguez.bluepot.application.usecase;

import com.kartaguez.bluepot.application.down.repository.PotGlobalVersionRepository;
import com.kartaguez.bluepot.application.down.repository.PotRepository;
import com.kartaguez.bluepot.application.down.repository.PotShareholderRepository;
import com.kartaguez.bluepot.application.usecase.dto.CreatePotDtoIn;
import com.kartaguez.bluepot.application.usecase.dto.CreatePotDtoOut;
import com.kartaguez.bluepot.application.usecase.worker.CreatePotUseCaseWorker;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
public class CreatePotUseCase {

    private final PotGlobalVersionRepository potGlobalversionRepository;
    private final PotRepository potRepository;
    private final PotShareholderRepository potShareholderRepository;

    public CreatePotDtoOut apply(CreatePotDtoIn createPotDtoIn) {

        //log.info(createPotDtoIn.toString());
        return CreatePotUseCaseWorker.getNewInstance(this.potGlobalversionRepository, this.potRepository, this.potShareholderRepository)
            .initiateWorkflow()
            .withCreatePotDtoIn(createPotDtoIn)
            .createPot()
            .savePot()
            .reply();

    }

}
