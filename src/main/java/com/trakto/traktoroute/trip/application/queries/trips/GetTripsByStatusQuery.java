package com.trakto.traktoroute.trip.application.queries.trips;

import com.trakto.traktoroute.trip.domain.model.enums.TripStatus;

public record GetTripsByStatusQuery(
        TripStatus status
) {
}