package com.trakto.traktoroute.profiles.interfaces.rest.resources.responses;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

@Schema(name = "ProfileResponse",
        description = "Profile information response")
public record ProfileResponse(

        @Schema(description = "Profile unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440000")
        UUID profileId,

        @Schema(description = "Associated IAM user unique identifier",
                example = "550e8400-e29b-41d4-a716-446655440001")
        UUID userReferenceId,

        @Schema(description = "Person given names",
                example = "Alexander José")
        String givenNames,

        @Schema(description = "Person paternal surname",
                example = "Fernandez")
        String paternalSurname,

        @Schema(description = "Person maternal surname",
                example = "García")
        String maternalSurname
) {
}