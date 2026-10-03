package com.trakto.traktoroute.profiles.application.queries;

import com.trakto.traktoroute.profiles.domain.model.valueobjects.UserReferenceId;

import java.util.Objects;

public record GetProfileByUserReferenceIdQuery(
        UserReferenceId userReferenceId
) {

    public GetProfileByUserReferenceIdQuery {
        Objects.requireNonNull(
                userReferenceId,
                "User reference ID cannot be null"
        );
    }
}