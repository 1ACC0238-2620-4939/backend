package com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle;

import java.math.BigDecimal;

public record VehicleCapacity(BigDecimal value) {

    public VehicleCapacity {
        if (value == null) {
            throw new IllegalArgumentException("Vehicle capacity cannot be null");
        }

        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Vehicle capacity must be greater than zero");
        }
    }
}