package com.trakto.traktoroute.trip.application.commands.stops;

import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopId;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.TripInstant;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;

public record FinishTripStopCommand(
        TripId tripId,
        StopId stopId,
        TripInstant endedAt
) {
}