package com.trakto.traktoroute.trip.application.commands.trips;

import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.*;

public record CreateTripCommand(
        DriverId driverId,
        VehicleId vehicleId,
        TripLocation origin,
        TripLocation destination,
        TripSchedule schedule,
        TripRoutePlan routePlan
) {
}