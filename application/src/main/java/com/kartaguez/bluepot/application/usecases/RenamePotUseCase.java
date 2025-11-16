package com.kartaguez.bluepot.application.usecases;

import com.kartaguez.bluepot.application.down.repository.PotRepository;
import com.kartaguez.bluepot.application.down.transaction.TransactionRunner;
import com.kartaguez.bluepot.application.services.PotGlobalVersionCalculator;
import com.kartaguez.bluepot.application.usecases.commands.RenamePotUseCaseCmd;
import com.kartaguez.bluepot.application.usecases.results.RenamePotUseCaseResult;
import com.kartaguez.bluepot.dto.PotDto;
import com.kartaguez.bluepot.model.Pot;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class RenamePotUseCase {

    private PotGlobalVersionCalculator potGlobalVersionCalculator;
    private PotRepository potRepository;
    private TransactionRunner tr;

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
        
        // 1. 1st transaction : fetch Pot
        Pot pot = tr.inTransaction(() -> 
            {
                return this.potRepository.fetchPotWithUuidIfBusinessVersionsDoMatch(renamePotUseCaseCmd.potUuid(), renamePotUseCaseCmd.expectedPotBusinessVersionValue(), renamePotUseCaseCmd.expectedPotBusinessVersionStamp());
            }
        );

        //2. Update Pot
        pot.rename(renamePotUseCaseCmd.potName());

        // 3. Generate new business version
        Long potNewBusinessVersionValue = this.potGlobalVersionCalculator.getNextPotBusinessVersionValue(renamePotUseCaseCmd.expectedPotBusinessVersionValue());
        String potNewBusinessVersionStamp = this.potGlobalVersionCalculator.getPotBusinessVersionStamp(potNewBusinessVersionValue, pot);

        // 4. 2nd transaction: save updated Pot
        tr.inTransaction(() -> 
            {
                this.potRepository.save(pot, renamePotUseCaseCmd.expectedPotBusinessVersionValue(), renamePotUseCaseCmd.expectedPotBusinessVersionStamp(), potNewBusinessVersionValue, potNewBusinessVersionStamp);
                return null;

            }
        );
    
        // Return
        return new RenamePotUseCaseResult(PotDto.of(pot));
    }

}
