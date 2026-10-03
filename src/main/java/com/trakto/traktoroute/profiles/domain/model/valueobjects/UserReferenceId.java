package com.trakto.traktoroute.profiles.domain.model.valueobjects;

import java.util.UUID;

public record UserReferenceId(UUID value) {

    public UserReferenceId {
        if (value == null) {
            throw new IllegalArgumentException("ProfileId cannot be null");
        }
    }
}