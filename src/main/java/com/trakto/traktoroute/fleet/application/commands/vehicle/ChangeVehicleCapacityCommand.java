package com.trakto.traktoroute.fleet.application.commands.vehicle;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleCapacity;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleId;

public record ChangeVehicleCapacityCommand(VehicleId vehicleId,
                                           VehicleCapacity capacity) {
}