package com.trakto.traktoroute.tracking.domain.model.events;

import com.trakto.traktoroute.tracking.domain.model.valueobjects.StopId;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TrackingId;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TripReferenceId;

import java.time.Instant;

public record TrackingStopFinishedEvent(
        TrackingId trackingId,
        TripReferenceId tripReferenceId,
        StopId stopId,
        Instant endedAt
) {
}