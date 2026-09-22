package com.trakto.traktoroute.trip.domain.model.valueobjects;

import java.time.Instant;

public record TripSchedule(Instant plannedStartAt,
                           Instant plannedEndAt) {
    public TripSchedule {
        if (plannedStartAt == null) {
            throw new IllegalArgumentException("Planned start at cannot be null");
        }
        if (plannedEndAt == null) {
            throw new IllegalArgumentException("Planned end at cannot be null");
        }
        if (plannedStartAt.isAfter(plannedEndAt)) {
            throw new IllegalArgumentException("Planned start at cannot be after planned end at");
        }
    }
}
