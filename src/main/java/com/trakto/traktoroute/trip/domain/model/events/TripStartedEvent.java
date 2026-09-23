package com.trakto.traktoroute.trip.domain.model.events;

import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;
import java.time.Instant;

public record TripStartedEvent(
        TripId tripId,
        Instant startedAt
) {}