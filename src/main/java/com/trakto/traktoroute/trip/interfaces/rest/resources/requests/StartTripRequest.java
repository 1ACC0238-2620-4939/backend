package com.trakto.traktoroute.trip.interfaces.rest.resources.requests;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(name = "StartTripRequest",
        description = "Request to start an existing trip")
public record StartTripRequest(

        @Schema(description = "Actual trip start time",
                example = "2026-09-24T18:05:00Z")
        Instant startedAt
) {
}