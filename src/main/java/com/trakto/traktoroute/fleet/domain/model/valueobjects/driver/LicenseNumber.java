package com.trakto.traktoroute.fleet.domain.model.valueobjects.driver;

import java.util.Locale;

public record LicenseNumber(String value) {

    public LicenseNumber {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("License number cannot be null or blank");
        }
        value = value.trim().toUpperCase(Locale.ROOT);
        if(!value.matches("[A-Z][0-9]{8}")) {
            throw new IllegalArgumentException("License value must be in the format of A12345678");
        }
    }
}