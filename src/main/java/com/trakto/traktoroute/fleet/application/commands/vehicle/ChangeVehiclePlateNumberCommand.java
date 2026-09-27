package com.trakto.traktoroute.fleet.application.commands.vehicle;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.PlateNumber;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleId;

public record ChangeVehiclePlateNumberCommand(VehicleId vehicleId,
                                               PlateNumber plateNumber) {
}