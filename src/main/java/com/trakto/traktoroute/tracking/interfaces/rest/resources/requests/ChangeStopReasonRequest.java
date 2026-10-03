package com.trakto.traktoroute.tracking.interfaces.rest.resources.requests;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import com.trakto.traktoroute.tracking.domain.model.enums.StopReason;

@Schema(name = "ChangeStopReasonRequest",
        description = "Request to change a stop reason")
public record ChangeStopReasonRequest(

        @NotNull(message = "Stop reason is required")
        @Schema(description = "New stop reason",
                example = "REST")
        StopReason reason
) {
}