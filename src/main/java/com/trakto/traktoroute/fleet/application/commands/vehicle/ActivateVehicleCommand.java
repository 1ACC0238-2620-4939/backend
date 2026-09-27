package com.trakto.traktoroute.fleet.application.commands.vehicle;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleId;

public record ActivateVehicleCommand(VehicleId vehicleId) {
}