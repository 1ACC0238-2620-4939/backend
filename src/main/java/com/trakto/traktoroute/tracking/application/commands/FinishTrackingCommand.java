package com.trakto.traktoroute.tracking.application.commands;

import com.trakto.traktoroute.tracking.domain.model.valueobjects.TrackingId;

import java.util.Objects;

public record FinishTrackingCommand(
        TrackingId trackingId
) {

    public FinishTrackingCommand {
        Objects.requireNonNull(
                trackingId,
                "Tracking ID is required"
        );
    }
}