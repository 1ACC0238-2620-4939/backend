package com.trakto.traktoroute.trip.domain.model.events;

import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopId;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;
import java.time.Instant;

public record TripStopRegisteredEvent(
        TripId tripId,
        StopId stopId,
        Instant startedAt
) {}