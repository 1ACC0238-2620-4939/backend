package com.trakto.traktoroute.trip.interfaces.rest.resources.requests;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.Instant;

@Schema(name = "CancelTripRequest",
        description = "Request to cancel a trip")
public record CancelTripRequest(

        @Schema(description = "Trip cancellation time",
                example = "2026-09-24T18:30:00Z")
        Instant cancelledAt
){
}