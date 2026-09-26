package com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle;

import java.util.UUID;

public record VehicleId(UUID value) {

    public VehicleId {
        if (value == null) {
            throw new IllegalArgumentException("Vehicle id cannot be null");
        }
    }

    public static VehicleId generate() {
        return new VehicleId(UUID.randomUUID());
    }
}