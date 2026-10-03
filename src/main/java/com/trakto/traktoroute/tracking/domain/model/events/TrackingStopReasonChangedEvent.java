package com.trakto.traktoroute.tracking.domain.model.events;

import com.trakto.traktoroute.tracking.domain.model.valueobjects.StopId;
import com.trakto.traktoroute.tracking.domain.model.enums.StopReason;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TrackingId;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TripReferenceId;

import java.time.Instant;

public record TrackingStopReasonChangedEvent(
        TrackingId trackingId,
        TripReferenceId tripReferenceId,
        StopId stopId,
        StopReason previousReason,
        StopReason newReason,
        Instant occurredAt
) {
}