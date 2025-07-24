package com.kartaguez.bluepot.crud.domain.model.mutation;

import java.util.List;

import com.kartaguez.bluepot.crud.domain.model.Pot;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PotMutationResultSet {

    private final Pot ObsoletePotInstance;
    private final Pot NewPotInstance;
    
    private final List<PotShareholderMutationResultSet> potShareholderMutationResultSet;
}
