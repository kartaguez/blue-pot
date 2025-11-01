package com.kartaguez.bluepot.application.usecases;

import com.kartaguez.bluepot.application.down.repository.PotGlobalVersionRepository;
import com.kartaguez.bluepot.application.down.repository.PotRepository;
import com.kartaguez.bluepot.application.dto.PotDto;
import com.kartaguez.bluepot.application.services.PotGlobalVersionCalculator;
import com.kartaguez.bluepot.application.usecases.commands.RenamePotUseCaseCmd;
import com.kartaguez.bluepot.application.usecases.results.RenamePotUseCaseResult;
import com.kartaguez.bluepot.model.Pot;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RenamePotUseCase {

    private PotGlobalVersionCalculator potGlobalVersionCalculator;
    private PotGlobalVersionRepository potGlobalVersionRepository;
    private PotRepository potRepository;

    public RenamePotUseCaseResult apply(RenamePotUseCaseCmd renamePotUseCaseCmd) {
        if (null == renamePotUseCaseCmd) {
            throw new IllegalArgumentException("renamePotUseCaseCmd cannot be null.");
        }
        if (null == renamePotUseCaseCmd.potUuid()) {
            throw new IllegalArgumentException("Pot Uuid cannot be null.");
        }
        if (null == renamePotUseCaseCmd.expectedPotBusinessVersionStamp()) {
            throw new IllegalArgumentException("Expected Pot Business Version Stamp cannot be null.");
        }
        if (null == renamePotUseCaseCmd.potName()) {
            throw new IllegalArgumentException("Pot Name cannot be null.");
        }
        
        if (!this.potGlobalVersionRepository.expectedAndCurrentBusinessVersionValueAndStampDoMatch(renamePotUseCaseCmd.potUuid(), renamePotUseCaseCmd.expectedPotBusinessVersionValue(), renamePotUseCaseCmd.expectedPotBusinessVersionStamp())) {
            throw new IllegalArgumentException("Expected Pot Business Version Value and Stamp are wrong.");
        }
        Pot pot = this.potRepository.fetchPotWithUuidAndBusinessVersionValue(renamePotUseCaseCmd.potUuid(), renamePotUseCaseCmd.expectedPotBusinessVersionValue());
        
        pot.rename(renamePotUseCaseCmd.potName());

        Long potBusinessVersionValue = this.potGlobalVersionCalculator.getNextPotBusinessVersionValue(renamePotUseCaseCmd.expectedPotBusinessVersionValue());
        String potBusinessVersionStamp = this.potGlobalVersionCalculator.getPotBusinessVersionStamp(potBusinessVersionValue, pot);
        this.potRepository.save(pot, potBusinessVersionValue);
        this.potGlobalVersionRepository.save(pot.getUuid(), potBusinessVersionValue, potBusinessVersionStamp);
    
        return new RenamePotUseCaseResult(PotDto.of(pot));
    }

}
