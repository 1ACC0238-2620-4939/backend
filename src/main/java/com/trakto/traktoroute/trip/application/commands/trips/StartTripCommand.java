package com.trakto.traktoroute.trip.application.commands.trips;

import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;

import java.time.Instant;

public record StartTripCommand(
        TripId tripId,
        Instant startedAt
) {
}