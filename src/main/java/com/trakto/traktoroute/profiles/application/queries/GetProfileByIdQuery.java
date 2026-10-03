package com.trakto.traktoroute.profiles.application.queries;

import com.trakto.traktoroute.profiles.domain.model.valueobjects.ProfileId;

import java.util.Objects;

public record GetProfileByIdQuery(
        ProfileId profileId
) {

    public GetProfileByIdQuery {
        Objects.requireNonNull(
                profileId,
                "Profile ID cannot be null"
        );
    }
}