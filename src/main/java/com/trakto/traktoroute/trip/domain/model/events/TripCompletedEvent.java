package com.trakto.traktoroute.trip.domain.model.events;

import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;
import java.time.Instant;

public record TripCompletedEvent(
        TripId tripId,
        Instant completedAt
) {}