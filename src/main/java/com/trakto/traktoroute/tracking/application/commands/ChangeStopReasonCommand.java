package com.trakto.traktoroute.tracking.application.commands;

import com.trakto.traktoroute.tracking.domain.model.enums.StopReason;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.StopId;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TrackingId;

import java.util.Objects;

public record ChangeStopReasonCommand(
        TrackingId trackingId,
        StopId stopId,
        StopReason reason
) {

    public ChangeStopReasonCommand {
        Objects.requireNonNull(
                trackingId,
                "Tracking ID is required"
        );

        Objects.requireNonNull(
                stopId,
                "Stop ID is required"
        );

        Objects.requireNonNull(
                reason,
                "Stop reason is required"
        );
    }
}