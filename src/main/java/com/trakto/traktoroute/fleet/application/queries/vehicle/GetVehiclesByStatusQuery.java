package com.trakto.traktoroute.fleet.application.queries.vehicle;

import com.trakto.traktoroute.fleet.domain.model.enums.VehicleStatus;

public record GetVehiclesByStatusQuery(VehicleStatus status) {
}