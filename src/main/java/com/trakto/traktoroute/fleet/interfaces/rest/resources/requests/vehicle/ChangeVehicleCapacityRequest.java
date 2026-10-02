package com.trakto.traktoroute.fleet.interfaces.rest.resources.requests.vehicle;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(name = "ChangeVehicleCapacityRequest",
        description = "Request to change a vehicle capacity")
public record ChangeVehicleCapacityRequest(

        @Schema(description = "New vehicle capacity",
                example = "2000.00",
                requiredMode = Schema.RequiredMode.REQUIRED)
        BigDecimal capacity
) {
}