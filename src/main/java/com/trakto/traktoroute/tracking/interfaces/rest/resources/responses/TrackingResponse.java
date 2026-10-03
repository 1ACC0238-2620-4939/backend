package com.trakto.traktoroute.tracking.interfaces.rest.resources.responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Schema(name = "TrackingResponse",
        description = "Tracking information response")
public record TrackingResponse(

        @Schema(description = "Tracking unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440000")
        UUID trackingId,

        @Schema(description = "Associated trip unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440001")
        UUID tripReferenceId,

        @Schema(description = "Current tracking status",
                example = "ACTIVE")
        String status,

        @Schema(description = "Last reported latitude",
                example = "-12.043",
                nullable = true)
        Double lastLatitude,

        @Schema(description = "Last reported longitude",
                example = "-76.971",
                nullable = true)
        Double lastLongitude,

        @Schema(description = "Date and time of the last GPS report",
                example = "2026-10-03T14:00:00Z",
                nullable = true)
        Instant lastPositionRecordedAt,

        @Schema(description = "Stationary period reference latitude",
                example = "-12.043",
                nullable = true)
        Double stationaryLatitude,

        @Schema(description = "Stationary period reference longitude",
                example = "-76.971",
                nullable = true)
        Double stationaryLongitude,

        @Schema(description = "Date and time when the stationary period began",
                example = "2026-10-03T13:50:00Z",
                nullable = true)
        Instant stationarySince,

        @Schema(description = "Automatically detected stops")
        List<TrackingStopResponse> stops
) {

    public TrackingResponse {
        stops = List.copyOf(stops);
    }
}