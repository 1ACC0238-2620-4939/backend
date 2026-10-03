package com.trakto.traktoroute.tracking.application.queries;

import com.trakto.traktoroute.tracking.domain.model.valueobjects.TrackingId;

import java.util.Objects;

public record GetTrackingByIdQuery(
        TrackingId trackingId
) {

    public GetTrackingByIdQuery {
        Objects.requireNonNull(
                trackingId,
                "Tracking ID is required"
        );
    }
}