package com.trakto.traktoroute.trip.domain.model.valueobjects;

public record TripCode(String tripCode) {
    public TripCode {
        if (tripCode == null || tripCode.isBlank()) {
            throw new IllegalArgumentException("Trip code cannot be null or empty");
        }
    }
}
