package com.trakto.traktoroute.trip.application.queries.trips;

import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;

public record GetTripByIdQuery(
        TripId tripId
) {
}