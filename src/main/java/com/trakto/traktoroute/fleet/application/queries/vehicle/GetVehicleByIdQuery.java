package com.trakto.traktoroute.fleet.application.queries.vehicle;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleId;

public record GetVehicleByIdQuery(VehicleId vehicleId) {
}