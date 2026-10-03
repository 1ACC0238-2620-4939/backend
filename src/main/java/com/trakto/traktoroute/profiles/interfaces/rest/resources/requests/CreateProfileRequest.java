package com.trakto.traktoroute.profiles.interfaces.rest.resources.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

@Schema(name = "CreateProfileRequest",
        description = "Request to create a profile")
public record CreateProfileRequest(

        @NotNull(message = "User reference ID is required")
        @Schema(description = "Associated IAM user unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440001")
        UUID userReferenceId,

        @NotBlank(message = "Given names are required")
        @Schema(description = "Person given names",
                example = "Alexander José")
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