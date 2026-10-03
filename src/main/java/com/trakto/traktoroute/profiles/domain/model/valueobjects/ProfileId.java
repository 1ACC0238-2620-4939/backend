package com.trakto.traktoroute.profiles.domain.model.valueobjects;


import java.util.UUID;

public record ProfileId(UUID value) {

    public ProfileId {
        if (value == null) {
            throw new IllegalArgumentException("ProfileId cannot be null");
        }
    }
    public static ProfileId generate() {
        return new ProfileId(UUID.randomUUID());
    }

}