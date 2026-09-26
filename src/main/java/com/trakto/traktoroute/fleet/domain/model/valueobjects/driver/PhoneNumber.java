package com.trakto.traktoroute.fleet.domain.model.valueobjects.driver;

public record PhoneNumber(String value) {

    public PhoneNumber {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Phone number cannot be null or blank");
        }

        value = value.trim();

        if (!value.matches("9[0-9]{8}")) {
            throw new IllegalArgumentException("Invalid phone number");
        }
    }
}