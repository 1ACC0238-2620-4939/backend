package com.trakto.traktoroute.tracking.application.queries;

import com.trakto.traktoroute.tracking.domain.model.valueobjects.TripReferenceId;

import java.util.Objects;

public record GetStopsByTripIdQuery(
        TripReferenceId tripReferenceId
) {

    public GetStopsByTripIdQuery {
        Objects.requireNonNull(
                tripReferenceId,
                "Trip reference ID is required"
        );
    }
}