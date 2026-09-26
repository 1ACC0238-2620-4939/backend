package com.trakto.traktoroute.trip.interfaces.rest.resources.responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Schema(name = "TripResponse",
        description = "Trip information response")
public record TripResponse(

        @Schema(description = "Trip unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440000")
        UUID tripId,

        @Schema(description = "Driver unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440001")
        UUID driverId,

        @Schema(description = "Vehicle unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440002")
        UUID vehicleId,

        @Schema(description = "Trip origin address",
                example = "Santa Anita, Lima")
        String originAddress,

        @Schema(description = "Trip origin latitude",
                example = "-12.043")
        BigDecimal originLatitude,

        @Schema(description = "Trip origin longitude",
                example = "-76.971")
        BigDecimal originLongitude,

        @Schema(description = "Trip destination address",
                example = "Miraflores, Lima")
        String destinationAddress,

        @Schema(description = "Trip destination latitude",
                example = "-12.121")
        BigDecimal destinationLatitude,

        @Schema(description = "Trip destination longitude",
                example = "-77.029")
        BigDecimal destinationLongitude,

        @Schema(description = "Current trip status",
                example = "IN_PROGRESS")
        String status,

        @Schema(description = "Scheduled trip start time",
                example = "2026-09-24T18:00:00Z")
        Instant scheduledAt,

        @Schema(description = "Actual trip start time",
                example = "2026-09-24T18:05:00Z")
        Instant startedAt,

        @Schema(description = "Trip completion time",
                example = "2026-09-24T19:20:00Z")
        Instant completedAt,

        @Schema(description = "Trip cancellation time",
                example = "2026-09-24T18:45:00Z")
        Instant cancelledAt,

        @Schema(description = "Route distance in kilometers",
                example = "21.50")
        BigDecimal distanceKm,

        @Schema(description = "Estimated route duration in minutes",
                example = "55")
        int durationMinutes,

        @Schema(description = "External route reference",
                example = "route-123")
        String routeReference,

        @Schema(description = "Date and time when the route was calculated",
                example = "2026-09-24T17:50:00Z")
        Instant calculatedAt
) {
}