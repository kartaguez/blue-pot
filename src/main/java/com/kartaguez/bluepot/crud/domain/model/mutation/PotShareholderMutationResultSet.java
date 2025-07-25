package com.kartaguez.bluepot.crud.domain.model.mutation;

import com.kartaguez.bluepot.crud.domain.model.object.PotShareholder;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PotShareholderMutationResultSet {
    
    private final PotShareholder ObsoletePotShareholderInstance;
    private final PotShareholder NewPotShareholderInstance;

}
