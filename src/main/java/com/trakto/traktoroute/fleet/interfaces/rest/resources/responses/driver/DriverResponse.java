package com.trakto.traktoroute.fleet.interfaces.rest.resources.responses.driver;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "DriverResponse",
        description = "Driver information response")
public record DriverResponse(

        @Schema(description = "Driver unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440000")
        UUID driverId,

        @Schema(description = "Profile unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440001")
        UUID profileId,

        @Schema(description = "Driver license number",
                example = "A12345678")
        String licenseNumber,

        @Schema(description = "Current driver status",
                example = "ACTIVE",
                allowableValues = {"ACTIVE", "INACTIVE"})
        String status

) {
}