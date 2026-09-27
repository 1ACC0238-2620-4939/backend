package com.trakto.traktoroute.fleet.domain.model.valueobjects.driver;

import java.util.UUID;

public record ProfileId(UUID value) {

    public ProfileId {
        if (value == null) {
            throw new IllegalArgumentException("ProfileId cannot be null");
        }
    }
}