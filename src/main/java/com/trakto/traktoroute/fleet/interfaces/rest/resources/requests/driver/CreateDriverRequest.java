package com.trakto.traktoroute.fleet.interfaces.rest.resources.requests.driver;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "CreateDriverRequest",
        description = "Request to create a driver")
public record CreateDriverRequest(

        @Schema(description = "Profile unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440001",
                requiredMode = Schema.RequiredMode.REQUIRED)
        UUID profileId,

        @Schema(description = "Driver license number",
                example = "A12345678",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String licenseNumber

) {
}