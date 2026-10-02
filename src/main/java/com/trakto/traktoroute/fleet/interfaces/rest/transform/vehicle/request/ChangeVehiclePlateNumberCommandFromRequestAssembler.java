package com.trakto.traktoroute.fleet.interfaces.rest.transform.vehicle.request;

import com.trakto.traktoroute.fleet.application.commands.vehicle.ChangeVehiclePlateNumberCommand;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.PlateNumber;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleId;
import com.trakto.traktoroute.fleet.interfaces.rest.resources.requests.vehicle.ChangeVehiclePlateNumberRequest;

import java.util.UUID;

public final class ChangeVehiclePlateNumberCommandFromRequestAssembler {

    private ChangeVehiclePlateNumberCommandFromRequestAssembler() {
    }

    public static ChangeVehiclePlateNumberCommand toCommand(
            UUID vehicleId,
            ChangeVehiclePlateNumberRequest request) {

        return new ChangeVehiclePlateNumberCommand(
                new VehicleId(vehicleId),
                new PlateNumber(request.plateNumber())
        );
    }
}