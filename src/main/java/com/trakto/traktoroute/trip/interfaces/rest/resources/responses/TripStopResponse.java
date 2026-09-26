package com.trakto.traktoroute.trip.interfaces.rest.resources.responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Schema(name = "TripStopResponse",
        description = "Trip stop information response")
public record TripStopResponse(

        @Schema(description = "Stop unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440010")
        UUID stopId,

        @Schema(description = "Stop latitude",
                example = "-12.089")
        BigDecimal latitude,

        @Schema(description = "Stop longitude",
                example = "-77.012")
        BigDecimal longitude,

        @Schema(description = "Date and time when the stop started",
                example = "2026-09-24T18:20:00Z")
        Instant startedAt,

        @Schema(description = "Date and time when the stop ended",
                example = "2026-09-24T18:35:00Z")
        Instant endedAt,

        @Schema(description = "Reason associated with the stop",
                example = "TRAFFIC")
        String reason
) {
}