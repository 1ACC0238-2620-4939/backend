package com.trakto.traktoroute.trip.interfaces.rest.transform;

import com.trakto.traktoroute.trip.domain.model.aggregates.Trip;
import com.trakto.traktoroute.trip.interfaces.rest.resources.responses.TripResponse;

public class TripResponseFromEntityAssembler {

    private TripResponseFromEntityAssembler() {
    }

    public static TripResponse toResponse(Trip trip) {

        return new TripResponse(
                trip.getId().value(),
                trip.getDriverId().value(),
                trip.getVehicleId().value(),

                trip.getOrigin().address(),
                trip.getOrigin().latitude(),
                trip.getOrigin().longitude(),

                trip.getDestination().address(),
                trip.getDestination().latitude(),
                trip.getDestination().longitude(),

                trip.getStatus().name(),

                trip.getSchedule().scheduledAt(),
                trip.getSchedule().startedAt(),
                trip.getSchedule().completedAt(),
                trip.getSchedule().cancelledAt(),

                trip.getRoutePlan().distanceKm(),
                trip.getRoutePlan().durationMinutes(),
                trip.getRoutePlan().routeReference(),
                trip.getRoutePlan().calculatedAt()
        );
    }
}