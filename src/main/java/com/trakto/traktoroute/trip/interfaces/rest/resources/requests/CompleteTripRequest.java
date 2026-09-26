package com.trakto.traktoroute.trip.interfaces.rest.resources.requests;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(name = "CompleteTripRequest",
        description = "Request to complete an active trip")
public record CompleteTripRequest(

        @Schema(description = "Trip completion time",
                example = "2026-09-24T20:30:00Z")
        Instant completedAt
){
}