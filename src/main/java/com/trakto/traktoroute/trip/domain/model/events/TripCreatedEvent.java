package com.trakto.traktoroute.trip.domain.model.events;

import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.DriverId;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.VehicleId;

import java.time.Instant;

public record TripCreatedEvent(
        TripId tripId,
        DriverId driverId,
        VehicleId vehicleId,
        Instant occurredAt
) {}