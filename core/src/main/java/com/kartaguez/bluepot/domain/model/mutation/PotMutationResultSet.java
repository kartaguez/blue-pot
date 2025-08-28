package com.kartaguez.bluepot.domain.model.mutation;

import java.util.List;

import com.kartaguez.bluepot.domain.model.Pot_old1;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PotMutationResultSet {

    private final Pot_old1 ObsoletePotInstance;
    private final Pot_old1 NewPotInstance;
    
    private final List<PotShareholderMutationResultSet> potShareholderMutationResultSet;
}
