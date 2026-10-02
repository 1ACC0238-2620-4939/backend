package com.trakto.traktoroute.fleet.interfaces.rest.transform.driver.path;

import com.trakto.traktoroute.fleet.application.commands.driver.ActivateDriverCommand;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.DriverId;

import java.util.UUID;

public final class ActivateDriverCommandFromPathAssembler {

    private ActivateDriverCommandFromPathAssembler() {
    }

    public static ActivateDriverCommand toCommand(UUID driverId) {
        return new ActivateDriverCommand(
                new DriverId(driverId)
        );
    }
}