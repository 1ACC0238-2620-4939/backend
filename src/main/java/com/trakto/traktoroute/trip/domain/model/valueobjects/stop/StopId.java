package com.trakto.traktoroute.trip.domain.model.valueobjects.stop;

import com.trakto.traktoroute.trip.domain.model.valueobjects.TripId;

import java.util.UUID;

public record StopId(UUID value) {

    public StopId {
        if (value == null)
            throw new IllegalArgumentException("TripId cannot be null");
    }
    public static TripId generate(){
        return new TripId(UUID.randomUUID());
    }
}
