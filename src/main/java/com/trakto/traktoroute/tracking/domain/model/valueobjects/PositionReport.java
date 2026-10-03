package com.trakto.traktoroute.tracking.domain.model.valueobjects;

import java.time.Instant;

public record PositionReport(GeoLocation location,
                             Instant recordedAt) {

    public PositionReport {
        if (location == null) {
            throw new IllegalArgumentException("Location is required");
        }

        if (recordedAt == null) {
            throw new IllegalArgumentException("Recorded time is required");
        }
    }
}