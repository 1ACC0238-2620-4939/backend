package com.trakto.traktoroute.fleet.application.commands.vehicle;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.PlateNumber;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleCapacity;

public record CreateVehicleCommand(PlateNumber plateNumber,
                                   VehicleCapacity capacity) {
}