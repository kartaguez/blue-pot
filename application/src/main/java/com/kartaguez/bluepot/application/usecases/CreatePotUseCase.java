package com.kartaguez.bluepot.application.usecases;

import java.util.HashMap;
import java.util.UUID;

import com.kartaguez.bluepot.application.down.repository.PotGlobalVersionRepository;
import com.kartaguez.bluepot.application.down.repository.PotRepository;
import com.kartaguez.bluepot.application.dto.PotDto;
import com.kartaguez.bluepot.application.services.PotGlobalVersionCalculator;
import com.kartaguez.bluepot.application.services.PotShareholderUuidGenerator;
import com.kartaguez.bluepot.application.services.PotUuidGenerator;
import com.kartaguez.bluepot.application.usecases.commands.CreatePotUseCaseCmd;
import com.kartaguez.bluepot.application.usecases.results.CreatePotUseCaseResult;
import com.kartaguez.bluepot.model.Pot;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreatePotUseCase {

    private PotUuidGenerator potUuidGenerator;
    private PotShareholderUuidGenerator potShareholderUuidGenerator;
    private PotGlobalVersionCalculator potGlobalVersionCalculator;
    private PotGlobalVersionRepository potGlobalVersionRepository;
    private PotRepository potRepository;

    public CreatePotUseCaseResult apply(CreatePotUseCaseCmd createPotUseCaseCmd) {
        if (null == createPotUseCaseCmd) {
            throw new IllegalArgumentException("createPotUseCaseCmd cannot be null.");
        }
        if (null == createPotUseCaseCmd.potName()) {
            throw new IllegalArgumentException("Pot Name cannot be null.");
        }
        
        Pot pot = Pot.builder(this.potUuidGenerator.getNewUuid(), createPotUseCaseCmd.potName()).build();
        
        HashMap<UUID, String> potShareholderNames = new HashMap<UUID, String>();
        if (null != createPotUseCaseCmd.potShareholderNames()) {
            createPotUseCaseCmd.potShareholderNames().stream().forEach(potShareholderName -> potShareholderNames.put(this.potShareholderUuidGenerator.getNewUuid(), potShareholderName));
        }
        pot.addPotShareholders(potShareholderNames);

        Long potBusinessVersionValue = this.potGlobalVersionCalculator.getInitPotBusinessVersionValue();
        String potBusinessVersionStamp = this.potGlobalVersionCalculator.getPotBusinessVersionStamp(potBusinessVersionValue, pot);
        this.potGlobalVersionRepository.save(pot.getUuid(), potBusinessVersionValue, potBusinessVersionStamp);
        this.potRepository.save(pot, potBusinessVersionValue);
    
        return new CreatePotUseCaseResult(PotDto.of(pot));
    }

}
