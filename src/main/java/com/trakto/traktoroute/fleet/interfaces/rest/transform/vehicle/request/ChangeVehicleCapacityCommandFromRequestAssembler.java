package com.trakto.traktoroute.fleet.interfaces.rest.transform.vehicle.request;

import com.trakto.traktoroute.fleet.application.commands.vehicle.ChangeVehicleCapacityCommand;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleCapacity;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleId;
import com.trakto.traktoroute.fleet.interfaces.rest.resources.requests.vehicle.ChangeVehicleCapacityRequest;

import java.util.UUID;

public final class ChangeVehicleCapacityCommandFromRequestAssembler {

    private ChangeVehicleCapacityCommandFromRequestAssembler() {
    }

    public static ChangeVehicleCapacityCommand toCommand(
            UUID vehicleId,
            ChangeVehicleCapacityRequest request) {

        return new ChangeVehicleCapacityCommand(
                new VehicleId(vehicleId),
                new VehicleCapacity(request.capacity())
        );
    }
}