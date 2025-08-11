package com.kartaguez.bluepot.application.usecase.worker;

import com.kartaguez.bluepot.application.down.repository.PotGlobalVersionRepository;
import com.kartaguez.bluepot.application.down.repository.PotRepository;
import com.kartaguez.bluepot.application.down.repository.PotShareholderRepository;
import com.kartaguez.bluepot.application.usecase.dto.CreatePotDtoIn;
import com.kartaguez.bluepot.application.usecase.dto.CreatePotDtoOut;
import com.kartaguez.bluepot.application.usecase.mapper.CreatePotDtoOutMapper;
import com.kartaguez.bluepot.domain.model.Pot;
import com.kartaguez.bluepot.domain.model.PotGlobalVersion;

import lombok.extern.log4j.Log4j2;

//@Log4j2
public class CreatePotUseCaseWorker {

    private PotGlobalVersionRepository potGlobalVersionRepository;
    private PotRepository potRepository;
    private PotShareholderRepository potShareholderRepository;
    private CreatePotDtoIn createPotDtoIn;
    private PotGlobalVersion potGlobalVersion;
    private Pot pot;

    public static CreatePotUseCaseWorker getNewInstance(PotGlobalVersionRepository _potGlobalVersionRepository, PotRepository _potRepository, PotShareholderRepository _potShareholderRepository) {
        return new CreatePotUseCaseWorker(_potGlobalVersionRepository, _potRepository, _potShareholderRepository);
    }

    private CreatePotUseCaseWorker(PotGlobalVersionRepository _potGlobalVersionRepository, PotRepository _potRepository, PotShareholderRepository _potShareholderRepository) {
        this.potGlobalVersionRepository = _potGlobalVersionRepository;
        this.potRepository = _potRepository;
        this.potShareholderRepository = _potShareholderRepository;
    }

    public Step1 initiateWorkflow() {
        return new privateStep1();
    }

    private CreatePotUseCaseWorker withCreatePotDtoIn(CreatePotDtoIn _createPotDtoIn) {
        if (null == _createPotDtoIn) {
            throw new IllegalArgumentException("Input Create Pot DTO cannot be null.");
        }
        if (null == _createPotDtoIn.getPotDto()) {
            throw new IllegalArgumentException("Input Create Pot Pot cannot be null.");
        }
        if (null == _createPotDtoIn.getPotDto().getName()) {
            throw new IllegalArgumentException("Input Create Pot Name cannot be null.");
        }

        this.createPotDtoIn = _createPotDtoIn;
    
        return this;
    }

    private CreatePotUseCaseWorker createPot() {
        this.pot = Pot.createRoot(this.createPotDtoIn.getPotDto().getName()).getNewPotInstance();
        //log.info(this.pot.toString());

        this.potGlobalVersion = PotGlobalVersion.create(this.pot.getUuid());
        //log.info(this.potGlobalVersion.toString());

        this.createPotDtoIn.getPotShareholderDtos().stream().forEach(potShareholderDto -> this.pot.addPotShareholder(potShareholderDto.getName()));
        //log.info(this.pot.toString());

        return this;
    }

    private CreatePotUseCaseWorker savePot() {
        this.potRepository.save(this.pot);
        this.potShareholderRepository.saveAll(this.pot.getPotShareholders().values());
        this.potGlobalVersionRepository.save(this.potGlobalVersion);
        return this;
    }

    public CreatePotDtoOut reply() {
        return CreatePotDtoOutMapper.getCreatePotDtoOut(this.pot);
    }

    // Workflow steps
    public interface Step1 {
        public Step2 withCreatePotDtoIn(CreatePotDtoIn _createPotDtoIn);
    }

    private class privateStep1 implements Step1 {
        public Step2 withCreatePotDtoIn(CreatePotDtoIn _createPotDtoIn) {
            CreatePotUseCaseWorker.this.withCreatePotDtoIn(_createPotDtoIn);
            return new privateStep2();
        }
    }

    public interface Step2 {
        public Step3 createPot();
    }

    private class privateStep2 implements Step2 {
        public Step3 createPot() {
            CreatePotUseCaseWorker.this.createPot();
            return new privateStep3();
        }
    }

    public interface Step3 {
        public Step4 savePot();
    }
    
    private class privateStep3 implements Step3 {
        public Step4 savePot() {
            CreatePotUseCaseWorker.this.savePot();
            return new privateStep4();
        }
    }
    
    public interface Step4 {
        public CreatePotDtoOut reply();
    }

    private class privateStep4 implements Step4 {
        public CreatePotDtoOut reply() {
            return CreatePotUseCaseWorker.this.reply();
        }
    }
}
