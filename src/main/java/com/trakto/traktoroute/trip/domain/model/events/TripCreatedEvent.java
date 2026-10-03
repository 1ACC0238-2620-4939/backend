package com.trakto.traktoroute.trip.domain.model.events;

import com.trakto.traktoroute.trip.domain.model.valueobjects.DriverId;
import com.trakto.traktoroute.trip.domain.model.valueobjects.TripId;
import com.trakto.traktoroute.trip.domain.model.valueobjects.VehicleId;

import java.time.Instant;

public record TripCreatedEvent(
        TripId tripId,
        DriverId driverId,
        VehicleId vehicleId,
        Instant occurredAt
) {}