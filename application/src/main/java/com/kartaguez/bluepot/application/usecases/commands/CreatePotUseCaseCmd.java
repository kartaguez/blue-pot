package com.kartaguez.bluepot.application.usecases.commands;

import java.util.List;

public record CreatePotUseCaseCmd(String potName, List<String> potShareholderNames) {
}
