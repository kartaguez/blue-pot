package com.kartaguez.bluepot.crud.domain.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kartaguez.bluepot.crud.domain.usecase.dto.CreatePotDtoIn;
import com.kartaguez.bluepot.crud.domain.usecase.dto.CreatePotDtoOut;
import com.kartaguez.bluepot.crud.domain.usecase.worker.factory.CreatePotUserCaseWorkerFactory;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreatePotUseCase {

    private final CreatePotUserCaseWorkerFactory createPotUserCaseWorkerFactory;

    @Transactional
    public CreatePotDtoOut apply(CreatePotDtoIn createPotDtoIn) {

        return this.createPotUserCaseWorkerFactory.getNewWorker()
            .initiateWorkflow()
            .withCreatePotDtoIn(createPotDtoIn)
            .createPot()
            .savePot()
            .reply();

    }

}
