package com.trakto.traktoroute.trip.domain.model.valueobjects.stop;

import java.time.Instant;

public record TripInstant(Instant value) {
    public TripInstant {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
    }
}
