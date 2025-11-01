package com.kartaguez.bluepot.application.services;

import com.kartaguez.bluepot.model.Pot;

public class PotGlobalVersionCalculator {

    private static final long INIT_POT_BUSINESS_VERSION_VALUE = 1L;

    public long getInitPotBusinessVersionValue() {
        return PotGlobalVersionCalculator.INIT_POT_BUSINESS_VERSION_VALUE;
    }

    public long getNextPotBusinessVersionValue(long currentPotBusinessVersionValue) {
        return currentPotBusinessVersionValue + 1;
    }

    // TODO: create stamp with hmac
    public String getPotBusinessVersionStamp(long potBusinessVersionValue, Pot pot) {
        if (null == pot) {
            throw new IllegalArgumentException("Pot cannot be null.");
        }
        if (null == pot.getUuid()) {
            throw new IllegalArgumentException("Pot Uuid cannot be null.");
        }
        StringBuilder stampBuilder = new StringBuilder();
        stampBuilder.append(pot.getUuid());
        stampBuilder.append("+");
        stampBuilder.append(potBusinessVersionValue);

        return stampBuilder.toString();
    }

}
