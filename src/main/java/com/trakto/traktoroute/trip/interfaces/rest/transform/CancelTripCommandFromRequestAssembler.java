package com.trakto.traktoroute.trip.interfaces.rest.transform;

import com.trakto.traktoroute.trip.application.commands.trips.CancelTripCommand;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;
import com.trakto.traktoroute.trip.interfaces.rest.resources.requests.CancelTripRequest;

import java.util.UUID;

public class CancelTripCommandFromRequestAssembler {

    private CancelTripCommandFromRequestAssembler() {}

    public static CancelTripCommand toCommand(UUID tripId,
                                              CancelTripRequest request) {
        return new CancelTripCommand(
                new TripId(tripId),
                request.cancelledAt());
    }
}