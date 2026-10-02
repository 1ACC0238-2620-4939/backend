package com.trakto.traktoroute.fleet.interfaces.rest.transform.vehicle.entities;

import com.trakto.traktoroute.fleet.domain.model.aggregates.Vehicle;
import com.trakto.traktoroute.fleet.interfaces.rest.resources.responses.vehicle.VehicleResponse;

public final class VehicleResponseFromEntityAssembler {

    private VehicleResponseFromEntityAssembler() {
    }

    public static VehicleResponse toResponse(Vehicle vehicle) {
        return new VehicleResponse(
                vehicle.getId().value(),
                vehicle.getPlateNumber().value(),
                vehicle.getCapacity().value(),
                vehicle.getStatus().name()
        );
    }
}