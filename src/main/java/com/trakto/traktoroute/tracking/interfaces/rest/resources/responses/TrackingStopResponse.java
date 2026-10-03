package com.trakto.traktoroute.tracking.interfaces.rest.resources.responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.UUID;

@Schema(name = "TrackingStopResponse",
        description = "Tracking stop information response")
public record TrackingStopResponse(

        @Schema(description = "Stop unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440002")
        UUID stopId,

        @Schema(description = "Stop latitude",
                example = "-12.043")
        double latitude,

        @Schema(description = "Stop longitude",
                example = "-76.971")
        double longitude,

        @Schema(description = "Date and time when the stop began",
                example = "2026-10-03T13:50:00Z")
        Instant startedAt,

        @Schema(description = "Date and time when the stop ended",
                example = "2026-10-03T14:05:00Z",
                nullable = true)
        Instant endedAt,

        @Schema(description = "Stop reason",
                example = "REST")
        String reason
) {
}