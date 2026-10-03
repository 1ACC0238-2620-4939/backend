package com.trakto.traktoroute.tracking.interfaces.rest.resources.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(name = "CreateTrackingRequest",
        description = "Request to create tracking for a trip")
public record CreateTrackingRequest(

        @NotNull(message = "Trip reference ID is required")
        @Schema(description = "Associated trip unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440001")
        UUID tripReferenceId
) {
}