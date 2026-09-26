package com.trakto.traktoroute.trip.interfaces.rest.transform;

import com.trakto.traktoroute.trip.domain.model.entities.TripStop;
import com.trakto.traktoroute.trip.interfaces.rest.resources.responses.TripStopResponse;

public class TripStopResponseFromEntityAssembler {

    private TripStopResponseFromEntityAssembler() {
    }

    public static TripStopResponse toResponse(TripStop stop) {

        return new TripStopResponse(
                stop.getId().value(),

                stop.getLocation().latitude(),
                stop.getLocation().longitude(),

                stop.getStartedAt().value(),

                stop.getEndedAt() != null
                        ? stop.getEndedAt().value()
                        : null,

                stop.getReason().name()
        );
    }
}