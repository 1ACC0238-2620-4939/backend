package com.trakto.traktoroute.fleet.interfaces.rest.resources.requests.vehicle;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(name = "CreateVehicleRequest",
        description = "Request to create a vehicle")
public record CreateVehicleRequest(

        @Schema(description = "Vehicle plate number",
                example = "ABC-123",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String plateNumber,

        @Schema(description = "Vehicle capacity",
                example = "1500.00",
                requiredMode = Schema.RequiredMode.REQUIRED)
        BigDecimal capacity

) {
}