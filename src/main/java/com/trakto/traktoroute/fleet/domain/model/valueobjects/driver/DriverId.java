package com.trakto.traktoroute.fleet.domain.model.valueobjects.driver;

import java.util.UUID;

public record DriverId(UUID value) {
    public DriverId {
        if (value == null) {
            throw new IllegalArgumentException("DriverId cannot be null");
        }
    }
    public static DriverId generate() {
        return new DriverId(UUID.randomUUID());
    }
}
