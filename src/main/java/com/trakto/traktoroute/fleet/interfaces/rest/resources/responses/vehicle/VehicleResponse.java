package com.trakto.traktoroute.fleet.interfaces.rest.resources.responses.vehicle;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.UUID;

@Schema(name = "VehicleResponse",
        description = "Vehicle information response")
public record VehicleResponse(

        @Schema(description = "Vehicle unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440000")
        UUID vehicleId,

        @Schema(description = "Vehicle plate number",
                example = "ABC-123")
        String plateNumber,

        @Schema(description = "Vehicle capacity",
                example = "1500.00")
        BigDecimal capacity,

        @Schema(description = "Current vehicle status",
                example = "ACTIVE",
                allowableValues = {"ACTIVE", "INACTIVE"})
        String status

) {
}