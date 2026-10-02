package com.trakto.traktoroute.fleet.interfaces.rest.transform.driver.path;

import com.trakto.traktoroute.fleet.application.commands.driver.DeactivateDriverCommand;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.DriverId;

import java.util.UUID;

public final class DeactivateDriverCommandFromPathAssembler {

    private DeactivateDriverCommandFromPathAssembler() {
    }

    public static DeactivateDriverCommand toCommand(UUID driverId) {
        return new DeactivateDriverCommand(
                new DriverId(driverId)
        );
    }
}