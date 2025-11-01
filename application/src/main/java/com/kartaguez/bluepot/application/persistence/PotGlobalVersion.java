package com.kartaguez.bluepot.application.persistence;

import java.util.UUID;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class PotGlobalVersion {

    private UUID potUuid;
    private Long potBusinessVersionValue;
    private String potBusinessVersionStamp;

}
