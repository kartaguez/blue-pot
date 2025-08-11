package com.kartaguez.bluepot.domain.model.mutation;

import java.util.List;

import com.kartaguez.bluepot.domain.model.Pot;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PotMutationResultSet {

    private final Pot ObsoletePotInstance;
    private final Pot NewPotInstance;
    
    private final List<PotShareholderMutationResultSet> potShareholderMutationResultSet;
}
