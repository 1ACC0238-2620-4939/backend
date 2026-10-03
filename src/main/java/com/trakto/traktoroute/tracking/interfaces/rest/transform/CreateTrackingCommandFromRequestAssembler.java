package com.trakto.traktoroute.tracking.interfaces.rest.transform;

import com.trakto.traktoroute.tracking.application.commands.CreateTrackingCommand;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TripReferenceId;
import com.trakto.traktoroute.tracking.interfaces.rest.resources.requests.CreateTrackingRequest;

public final class CreateTrackingCommandFromRequestAssembler {

    private CreateTrackingCommandFromRequestAssembler() {
    }

    public static CreateTrackingCommand toCommand(
            CreateTrackingRequest request
    ) {
        return new CreateTrackingCommand(
                new TripReferenceId(request.tripReferenceId())
        );
    }
}