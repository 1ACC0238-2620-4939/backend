package com.trakto.traktoroute.trip.domain.model.valueobjects.trip;

import java.math.BigDecimal;
import java.time.Instant;

public record TripRoutePlan(BigDecimal distanceKm,
                            int durationMinutes,
                            String routeReference,
                            Instant calculatedAt) {
    public TripRoutePlan {
        if (distanceKm == null || distanceKm.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Distance must be greater than zero");
        }
        if (durationMinutes <= 0) {
            throw new IllegalArgumentException("Duration must be greater than zero");
        }
        if (routeReference == null || routeReference.isBlank()) {
            throw new IllegalArgumentException("Route reference cannot be null or blank");
        }
        if (calculatedAt == null) {
            throw new IllegalArgumentException("Calculated at cannot be null");
        }

        routeReference = routeReference.trim();
    }
}
