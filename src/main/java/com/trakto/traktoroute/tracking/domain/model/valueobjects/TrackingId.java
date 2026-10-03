package com.trakto.traktoroute.tracking.domain.model.valueobjects;

import java.util.UUID;

public record TrackingId(UUID value) {

    public TrackingId {
        if (value == null) {
            throw new IllegalArgumentException("Tracking ID is required");
        }
    }

    public static TrackingId generate() {
        return new TrackingId(UUID.randomUUID());
    }
}