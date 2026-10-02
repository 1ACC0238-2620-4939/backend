package com.trakto.traktoroute.fleet.interfaces.rest.resources.requests.driver;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "ChangeDriverLicenseNumberRequest",
        description = "Request to change a driver license number")
public record ChangeDriverLicenseNumberRequest(

        @Schema(description = "New driver license number",
                example = "B87654321",
                requiredMode = Schema.RequiredMode.REQUIRED)
        String licenseNumber

) {
}