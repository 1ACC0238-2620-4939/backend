package com.trakto.traktoroute.trip.interfaces.rest.transform;

import com.trakto.traktoroute.trip.application.commands.trips.CompleteTripCommand;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;
import com.trakto.traktoroute.trip.interfaces.rest.resources.requests.CompleteTripRequest;

import java.util.UUID;

public class CompleteTripCommandFromRequestAssembler {

    private CompleteTripCommandFromRequestAssembler() {}

    public static CompleteTripCommand toCommand(UUID tripId,
                                                CompleteTripRequest request) {

        return new CompleteTripCommand(
                new TripId(tripId),
                request.completedAt());
    }
}