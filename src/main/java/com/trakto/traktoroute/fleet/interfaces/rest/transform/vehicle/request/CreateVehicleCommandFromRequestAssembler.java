package com.trakto.traktoroute.fleet.interfaces.rest.transform.vehicle.request;

import com.trakto.traktoroute.fleet.application.commands.vehicle.CreateVehicleCommand;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.PlateNumber;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleCapacity;
import com.trakto.traktoroute.fleet.interfaces.rest.resources.requests.vehicle.CreateVehicleRequest;

public final class CreateVehicleCommandFromRequestAssembler {

    private CreateVehicleCommandFromRequestAssembler() {
    }

    public static CreateVehicleCommand toCommand(CreateVehicleRequest request) {
        return new CreateVehicleCommand(
                new PlateNumber(request.plateNumber()),
                new VehicleCapacity(request.capacity())
        );
    }
}