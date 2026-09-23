package com.trakto.traktoroute.trip.domain.model.valueobjects.stop;

import java.util.UUID;

public record StopId(UUID value) {

    public StopId {
        if (value == null)
            throw new IllegalArgumentException("StopId cannot be null");
    }
    public static StopId generate(){
        return new StopId(UUID.randomUUID());
    }
}
