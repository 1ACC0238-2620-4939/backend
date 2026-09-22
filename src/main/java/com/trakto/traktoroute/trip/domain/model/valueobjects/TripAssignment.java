package com.trakto.traktoroute.trip.domain.model.valueobjects;

import java.util.UUID;

public record TripAssignment(UUID driverId,
                             UUID vehicleId) {
    public TripAssignment {
        if (driverId == null) {
            throw new IllegalArgumentException("Driver id cannot be null");
        }
        if (vehicleId == null) {
            throw new IllegalArgumentException("Vehicle id cannot be null");
        }
    }
}
