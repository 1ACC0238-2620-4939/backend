package com.trakto.traktoroute.fleet.interfaces.rest.resources.requests.vehicle;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ChangeVehiclePlateNumberRequest",
        description = "Request to change a vehicle plate number")
public record ChangeVehiclePlateNumberRequest(

        @Schema(description = "New vehicle plate number",
                example = "XYZ-456",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String plateNumber

) {
}