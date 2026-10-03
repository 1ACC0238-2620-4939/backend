package com.trakto.traktoroute.tracking.domain.model.events;

import com.trakto.traktoroute.tracking.domain.model.valueobjects.TrackingId;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TripReferenceId;

import java.time.Instant;

public record TrackingFinishedEvent(
        TrackingId trackingId,
        TripReferenceId tripReferenceId,
        Instant finishedAt
) {
}