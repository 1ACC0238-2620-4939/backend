package com.trakto.traktoroute.trip.domain.model.valueobjects.stop;

import java.time.Instant;
import java.util.Objects;

public record TripInstant(Instant value) {
    public TripInstant {
        if (value == null) {
            throw new IllegalArgumentException("value cannot be null");
        }
    }
        public boolean isBefore(TripInstant other) {
        Objects.requireNonNull(other);
        return value.isBefore(other.value());
    }
}
