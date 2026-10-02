package com.trakto.traktoroute.fleet.interfaces.rest.transform.vehicle.path;

import com.trakto.traktoroute.fleet.application.commands.vehicle.ActivateVehicleCommand;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleId;

import java.util.UUID;

public final class ActivateVehicleCommandFromPathAssembler {

    private ActivateVehicleCommandFromPathAssembler() {
    }

    public static ActivateVehicleCommand toCommand(UUID vehicleId) {
        return new ActivateVehicleCommand(
                new VehicleId(vehicleId)
        );
    }
}