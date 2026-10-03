package com.trakto.traktoroute.trip.application.queries;

import com.trakto.traktoroute.trip.domain.model.valueobjects.TripId;

public record GetTripByIdQuery(TripId tripId) {
}