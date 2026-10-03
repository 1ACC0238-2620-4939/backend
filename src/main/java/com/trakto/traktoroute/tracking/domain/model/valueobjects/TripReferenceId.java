package com.trakto.traktoroute.tracking.domain.model.valueobjects;

import java.util.UUID;

public record TripReferenceId(UUID value) {

    public TripReferenceId {
        if (value == null) {
            throw new IllegalArgumentException("Trip reference ID is required");
        }
    }
}