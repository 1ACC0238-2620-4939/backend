package com.trakto.traktoroute.tracking.application.commands;

import com.trakto.traktoroute.tracking.domain.model.valueobjects.TripReferenceId;

import java.util.Objects;

public record CreateTrackingCommand(TripReferenceId tripReferenceId) {

    public CreateTrackingCommand {
        Objects.requireNonNull(tripReferenceId, "Trip reference ID is required");
    }
}