package com.trakto.traktoroute.tracking.application.commands;

import com.trakto.traktoroute.tracking.domain.model.valueobjects.PositionReport;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TrackingId;

import java.util.Objects;

public record RegisterPositionCommand(
        TrackingId trackingId,
        PositionReport positionReport
) {

    public RegisterPositionCommand {
        Objects.requireNonNull(
                trackingId,
                "Tracking ID is required"
        );

        Objects.requireNonNull(
                positionReport,
                "Position report is required"
        );
    }
}