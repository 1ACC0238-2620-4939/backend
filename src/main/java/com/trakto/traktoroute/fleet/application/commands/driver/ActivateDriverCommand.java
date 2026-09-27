package com.trakto.traktoroute.fleet.application.commands.driver;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.DriverId;

public record ActivateDriverCommand(DriverId driverId) {
}