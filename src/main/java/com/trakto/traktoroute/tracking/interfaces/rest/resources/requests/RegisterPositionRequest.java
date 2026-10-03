package com.trakto.traktoroute.tracking.interfaces.rest.resources.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

@Schema(name = "RegisterPositionRequest",
        description = "Request to register a GPS position")
public record RegisterPositionRequest(

        @NotNull(message = "Latitude is required")
        @DecimalMin(value = "-90.0",
                message = "Latitude must be at least -90")
        @DecimalMax(value = "90.0",
                message = "Latitude must be at most 90")
        @Schema(description = "Reported latitude",
                example = "-12.043")
        Double latitude,

        @NotNull(message = "Longitude is required")
        @DecimalMin(value = "-180.0",
                message = "Longitude must be at least -180")
        @DecimalMax(value = "180.0",
                message = "Longitude must be at most 180")
        @Schema(description = "Reported longitude",
                example = "-76.971")
        Double longitude,

        @NotNull(message = "Position report time is required")
        @Schema(description = "Date and time when the GPS position was recorded",
                example = "2026-10-03T14:20:00Z")
        Instant recordedAt
) {
}