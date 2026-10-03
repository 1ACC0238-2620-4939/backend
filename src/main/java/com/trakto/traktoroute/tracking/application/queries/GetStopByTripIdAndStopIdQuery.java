package com.trakto.traktoroute.tracking.application.queries;

import com.trakto.traktoroute.tracking.domain.model.valueobjects.StopId;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TripReferenceId;

import java.util.Objects;

public record GetStopByTripIdAndStopIdQuery(
        TripReferenceId tripReferenceId,
        StopId stopId
) {

    public GetStopByTripIdAndStopIdQuery {
        Objects.requireNonNull(
                tripReferenceId,
                "Trip reference ID is required"
        );

        Objects.requireNonNull(
                stopId,
                "Stop ID is required"
        );
    }
}