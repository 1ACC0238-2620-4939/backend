package com.trakto.traktoroute.trip.interfaces.rest.transform;

import com.trakto.traktoroute.trip.application.commands.stops.StartTripStopCommand;
import com.trakto.traktoroute.trip.domain.model.enums.StopReason;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopLocation;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.TripInstant;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;
import com.trakto.traktoroute.trip.interfaces.rest.resources.requests.StartTripStopRequest;

import java.util.UUID;

public class StartTripStopCommandFromRequestAssembler {

    private StartTripStopCommandFromRequestAssembler() {}

    public static StartTripStopCommand toCommand(UUID tripId,
                                                 StartTripStopRequest request) {

        var location = new StopLocation(
                request.latitude(),
                request.longitude());

        var startedAt = new TripInstant(
                request.startedAt());

        var reason = StopReason.valueOf(
                request.reason().toUpperCase());

        return new StartTripStopCommand(
                new TripId(tripId),
                location,
                startedAt,
                reason
        );
    }
}