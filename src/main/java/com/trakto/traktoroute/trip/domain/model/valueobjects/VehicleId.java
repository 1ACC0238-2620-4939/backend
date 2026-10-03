package com.trakto.traktoroute.trip.domain.model.valueobjects;


import java.util.UUID;

public record VehicleId(UUID value){

    public VehicleId {
        if (value == null)
            throw new IllegalArgumentException("TripId cannot be null");
    }
}
