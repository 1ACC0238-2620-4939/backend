package com.trakto.traktoroute.trip.application.commands;

import com.trakto.traktoroute.trip.domain.model.valueobjects.TripId;

import java.time.Instant;

public record StartTripCommand(
        TripId tripId,
        Instant startedAt
) {
}