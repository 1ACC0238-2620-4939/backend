package com.trakto.traktoroute.trip.interfaces.rest.transform;

import com.trakto.traktoroute.trip.application.commands.CompleteTripCommand;
import com.trakto.traktoroute.trip.domain.model.valueobjects.TripId;
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