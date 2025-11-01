package com.kartaguez.bluepot.application.usecases.commands;

import java.util.UUID;

public record RenamePotUseCaseCmd(UUID potUuid, Long expectedPotBusinessVersionValue, String expectedPotBusinessVersionStamp, String potName) {
}
