package com.trakto.traktoroute.trip.application.queries.stops;

import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopId;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;

public record GetStopByTripIdAndStopIdQuery(
        TripId tripId,
        StopId stopId
) {
}