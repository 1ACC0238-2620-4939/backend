package com.trakto.traktoroute.fleet.domain.model.valueobjects.driver;

public record FullName(String firstName,
                       String lastName) {

    public FullName {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("First name cannot be null or blank");
        }

        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("Last name cannot be null or blank");
        }

        firstName = firstName.trim();
        lastName = lastName.trim();

        if (firstName.length() < 2 || firstName.length() > 30) {
            throw new IllegalArgumentException(
                    "First name must contain between 2 and 30 characters");
        }

        if (lastName.length() < 2 || lastName.length() > 30) {
            throw new IllegalArgumentException(
                    "Last name must contain between 2 and 30 characters");
        }
    }

    public String fullName() {
        return "%s %s".formatted(firstName,lastName);
    }
}