package com.trakto.traktoroute.profiles.interfaces.rest.resources.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

@Schema(name = "ChangeProfileFullNameRequest",
        description = "Request to change a profile full name")
public record ChangeProfileFullNameRequest(

        @NotBlank(message = "Given names are required")
        @Schema(description = "Person given names",
                example = "Alexander José Luis")
        String givenNames,

        @NotBlank(message = "Paternal surname is required")
        @Schema(description = "Person paternal surname",
                example = "Fernandez")
        String paternalSurname,

        @NotBlank(message = "Maternal surname is required")
        @Schema(description = "Person maternal surname",
                example = "García")
        String maternalSurname
) {
}