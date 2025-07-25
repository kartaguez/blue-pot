package com.kartaguez.bluepot.crud.domain.usecase.worker;

import com.kartaguez.bluepot.crud.domain.bottom.repository.PotGlobalVersionRepository;
import com.kartaguez.bluepot.crud.domain.bottom.repository.PotRepository;
import com.kartaguez.bluepot.crud.domain.bottom.repository.PotShareholderRepository;
import com.kartaguez.bluepot.crud.domain.model.object.Pot;
import com.kartaguez.bluepot.crud.domain.model.object.PotGlobalVersion;
import com.kartaguez.bluepot.crud.domain.usecase.dto.CreatePotDtoIn;
import com.kartaguez.bluepot.crud.domain.usecase.dto.CreatePotDtoOut;
import com.kartaguez.bluepot.crud.domain.usecase.mapper.CreatePotDtoInMapper;
import com.kartaguez.bluepot.crud.domain.usecase.mapper.CreatePotDtoOutMapper;

public class CreatePotUseCaseWorker {

    private PotGlobalVersionRepository potGlobalVersionRepository;
    private PotRepository potRepository;
    private PotShareholderRepository potShareholderRepository;
    private CreatePotDtoIn createPotDtoIn;
    private CreatePotDtoInMapper createPotDtoInMapper;
    private CreatePotDtoOutMapper createPotDtoOutMapper;
    private PotGlobalVersion potGlobalVersion;
    private Pot pot;

    public static CreatePotUseCaseWorker getNewInstance(PotGlobalVersionRepository _potGlobalVersionRepository, PotRepository _potRepository, PotShareholderRepository _potShareholderRepository, CreatePotDtoInMapper _createPotDtoInMapper, CreatePotDtoOutMapper _createPotDtoOutMapper) {
        return new CreatePotUseCaseWorker(_potGlobalVersionRepository, _potRepository, _potShareholderRepository, _createPotDtoInMapper, _createPotDtoOutMapper);
    }

    private CreatePotUseCaseWorker(PotGlobalVersionRepository _potGlobalVersionRepository, PotRepository _potRepository, PotShareholderRepository _potShareholderRepository, CreatePotDtoInMapper _createPotDtoInMapper, CreatePotDtoOutMapper _createPotDtoOutMapper) {
        this.potGlobalVersionRepository = _potGlobalVersionRepository;
        this.potRepository = _potRepository;
        this.potShareholderRepository = _potShareholderRepository;
        this.createPotDtoInMapper = _createPotDtoInMapper;
        this.createPotDtoOutMapper = _createPotDtoOutMapper;
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
    
        return this;
    }

    private CreatePotUseCaseWorker createPot() {
        this.pot = Pot.createRoot(this.createPotDtoIn.getPotDto().getName()).getNewPotInstance();
        this.potGlobalVersion = PotGlobalVersion.create(this.pot.getUuid());

        this.createPotDtoIn.getPotShareholderDtos().stream().forEach(potShareholderDto -> this.pot.addPotShareholder(potShareholderDto.getName()));
       
        return this;
    }

    private CreatePotUseCaseWorker savePot() {
        this.potRepository.create(this.pot);
        this.potShareholderRepository.saveAll(this.pot.getPotShareholders().values());
        this.potGlobalVersionRepository.save(this.potGlobalVersion);
        return this;
    }

    public CreatePotDtoOut reply() {
        return CreatePotUseCaseWorker.this.createPotDtoOutMapper.getCreatePotDtoOut(this.pot);
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
