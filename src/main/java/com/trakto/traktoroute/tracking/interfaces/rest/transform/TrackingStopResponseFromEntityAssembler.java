package com.trakto.traktoroute.tracking.interfaces.rest.transform;

import com.trakto.traktoroute.tracking.domain.model.entities.TrackingStop;
import com.trakto.traktoroute.tracking.interfaces.rest.resources.responses.TrackingStopResponse;

public final class TrackingStopResponseFromEntityAssembler {

    private TrackingStopResponseFromEntityAssembler() {
    }

    public static TrackingStopResponse toResourceFromEntity(
            TrackingStop stop
    ) {
        return new TrackingStopResponse(
                stop.getStopId().value(),
                stop.getLocation().latitude(),
                stop.getLocation().longitude(),
                stop.getStartedAt(),
                stop.getEndedAt(),
                stop.getReason().name()
        );
    }
}