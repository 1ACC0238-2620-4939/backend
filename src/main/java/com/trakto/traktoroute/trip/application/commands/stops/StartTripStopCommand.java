package com.trakto.traktoroute.trip.application.commands.stops;

import com.trakto.traktoroute.trip.domain.model.enums.StopReason;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopLocation;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.TripInstant;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;

public record StartTripStopCommand(
        TripId tripId,
        StopLocation location,
        TripInstant startedAt,
        StopReason reason
) {
}