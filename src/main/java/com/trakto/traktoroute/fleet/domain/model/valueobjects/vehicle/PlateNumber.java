package com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle;

import java.util.Locale;

public record PlateNumber(String value) {

    public PlateNumber {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Plate number cannot be null or blank");
        }

        value = value
                .trim()
                .toUpperCase(Locale.ROOT)
                .replace("-", "");

        if (!value.matches("[A-Z]{3}[0-9]{3}")) {
            throw new IllegalArgumentException("Plate number must be in the format ABC-123");
        }
    }
}