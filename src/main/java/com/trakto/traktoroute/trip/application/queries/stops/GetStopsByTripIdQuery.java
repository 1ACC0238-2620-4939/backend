package com.trakto.traktoroute.trip.application.queries.stops;

import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;

public record GetStopsByTripIdQuery(
        TripId tripId
) {
}