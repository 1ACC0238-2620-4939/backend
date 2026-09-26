package com.trakto.traktoroute.trip.interfaces.rest.transform;

import com.trakto.traktoroute.trip.application.commands.trips.StartTripCommand;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;
import com.trakto.traktoroute.trip.interfaces.rest.resources.requests.StartTripRequest;

import java.util.UUID;

public class StartTripCommandFromRequestAssembler {

    private StartTripCommandFromRequestAssembler() {}

    public static StartTripCommand toCommand(UUID tripId,
                                              StartTripRequest request) {

        return new StartTripCommand(
                new TripId(tripId),
                request.startedAt());
    }
}