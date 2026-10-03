package com.trakto.traktoroute.profiles.domain.model.valueobjects;

public record PersonName(String givenNames,
                         String paternalSurname,
                         String maternalSurname) {

    public PersonName {
        if (givenNames == null || givenNames.isBlank()) {
            throw new IllegalArgumentException("Given name must not be null or blank");
        }
        if (paternalSurname == null || paternalSurname.isBlank()) {
            throw new IllegalArgumentException("Paternal surname must not be null or blank");
        }
        if (maternalSurname == null || maternalSurname.isBlank()) {
            throw new IllegalArgumentException("Maternal surname must not be null or blank");
        }
    }

    public String getFullName() {
        return "%s %s %s".formatted(givenNames, paternalSurname, maternalSurname);
    }
}