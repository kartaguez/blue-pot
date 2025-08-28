package com.kartaguez.bluepot.domain._to_delete.mutation;

import com.kartaguez.bluepot.domain._to_delete.model.PotShareholder_old1;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PotShareholderMutationResultSet {
    
    private final PotShareholder_old1 ObsoletePotShareholderInstance;
    private final PotShareholder_old1 NewPotShareholderInstance;

}
