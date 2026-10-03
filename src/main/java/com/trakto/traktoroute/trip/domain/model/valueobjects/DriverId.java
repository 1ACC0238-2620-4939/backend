package com.trakto.traktoroute.trip.domain.model.valueobjects;


import java.util.UUID;

public record DriverId(UUID value){

    public DriverId {
        if (value == null)
            throw new IllegalArgumentException("TripId cannot be null");
    }
}
