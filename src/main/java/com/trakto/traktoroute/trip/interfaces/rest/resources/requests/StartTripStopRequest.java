package com.trakto.traktoroute.trip.interfaces.rest.resources.requests;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;

@Schema(name = "StartTripStopRequest",
        description = "Request to register the start of a detected trip stop")
public record StartTripStopRequest(

        @Schema(description = "Stop address",
                example = "Av. Javier Prado, Lima")
        String address,

        @Schema(description = "Stop latitude",
                example = "-12.089")
        BigDecimal latitude,

        @Schema(description = "Stop longitude",
                example = "-77.012")
        BigDecimal longitude,

        @Schema(description = "Date and time when the stop started",
                example = "2026-09-24T18:20:00Z")
        Instant startedAt,

        @Schema(description = "Reason associated with the stop",
                example = "TRAFFIC")
        String reason
) {
}