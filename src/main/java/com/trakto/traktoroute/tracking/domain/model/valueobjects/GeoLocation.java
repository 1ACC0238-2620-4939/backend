package com.trakto.traktoroute.tracking.domain.model.valueobjects;

public record GeoLocation(double latitude,
                          double longitude) {

    public GeoLocation {
        if (!Double.isFinite(latitude) || latitude < -90 || latitude > 90) {
            throw new IllegalArgumentException("Latitude must be between -90 and 90");
        }

        if (!Double.isFinite(longitude) || longitude < -180 || longitude > 180) {
            throw new IllegalArgumentException("Longitude must be between -180 and 180");
        }
    }
}