package com.trakto.traktoroute.trip.domain.model.valueobjects;

import java.util.UUID;

public record TripId(UUID value){
    public TripId {
        if (value == null)
            throw new IllegalArgumentException("TripId cannot be null");
    }
    public static TripId generate(){
        return new TripId(UUID.randomUUID());
    }
}
