package com.trakto.traktoroute.tracking.domain.model.valueobjects;

import java.util.UUID;

public record StopId(UUID value) {

    public StopId {
        if (value == null) {
            throw new IllegalArgumentException("Stop ID is required");
        }
    }

    public static StopId generate() {
        return new StopId(UUID.randomUUID());
    }
}