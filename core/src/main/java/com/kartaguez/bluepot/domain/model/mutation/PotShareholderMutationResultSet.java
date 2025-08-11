package com.kartaguez.bluepot.domain.model.mutation;

import com.kartaguez.bluepot.domain.model.PotShareholder;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PotShareholderMutationResultSet {
    
    private final PotShareholder ObsoletePotShareholderInstance;
    private final PotShareholder NewPotShareholderInstance;

}
