package com.trakto.traktoroute.trip.interfaces.rest.resources.requests;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(name = "FinishTripStopRequest",
        description = "Request to finish an active trip stop")
public record FinishTripStopRequest(

        @Schema(description = "Date and time when the stop ended",
                example = "2026-09-24T18:35:00Z")
        Instant endedAt
) {
}