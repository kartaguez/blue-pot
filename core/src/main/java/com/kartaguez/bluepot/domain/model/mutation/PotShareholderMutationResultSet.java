package com.kartaguez.bluepot.domain.model.mutation;

import com.kartaguez.bluepot.domain.model.PotShareholder_old1;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PotShareholderMutationResultSet {
    
    private final PotShareholder_old1 ObsoletePotShareholderInstance;
    private final PotShareholder_old1 NewPotShareholderInstance;

}
