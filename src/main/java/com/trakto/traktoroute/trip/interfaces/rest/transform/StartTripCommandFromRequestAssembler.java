package com.trakto.traktoroute.trip.interfaces.rest.transform;

import com.trakto.traktoroute.trip.application.commands.StartTripCommand;
import com.trakto.traktoroute.trip.domain.model.valueobjects.TripId;
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