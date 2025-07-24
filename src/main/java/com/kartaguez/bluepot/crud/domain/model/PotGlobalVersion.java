package com.kartaguez.bluepot.crud.domain.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PotGlobalVersion {

    private long value;

    public long incrementVersion() {
        return this.value++;
    }

}
