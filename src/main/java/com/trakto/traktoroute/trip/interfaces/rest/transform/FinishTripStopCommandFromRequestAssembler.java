package com.trakto.traktoroute.trip.interfaces.rest.transform;

import com.trakto.traktoroute.trip.application.commands.stops.FinishTripStopCommand;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopId;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.TripInstant;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;
import com.trakto.traktoroute.trip.interfaces.rest.resources.requests.FinishTripStopRequest;

import java.util.UUID;

public class FinishTripStopCommandFromRequestAssembler {

    private FinishTripStopCommandFromRequestAssembler() {}

    public static FinishTripStopCommand toCommand(
            UUID tripId,
            UUID stopId,
            FinishTripStopRequest request
    ) {

        return new FinishTripStopCommand(
                new TripId(tripId),
                new StopId(stopId),
                new TripInstant(request.endedAt())
        );
    }
}