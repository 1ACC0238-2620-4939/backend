package com.trakto.traktoroute.fleet.interfaces.rest.transform.vehicle.path;

import com.trakto.traktoroute.fleet.application.commands.vehicle.DeactivateVehicleCommand;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleId;

import java.util.UUID;

public final class DeactivateVehicleCommandFromPathAssembler {

    private DeactivateVehicleCommandFromPathAssembler() {
    }

    public static DeactivateVehicleCommand toCommand(UUID vehicleId) {
        return new DeactivateVehicleCommand(
                new VehicleId(vehicleId)
        );
    }
}