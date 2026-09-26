package com.trakto.traktoroute.trip.interfaces.rest.transform;

import com.trakto.traktoroute.trip.application.commands.trips.CreateTripCommand;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.DriverId;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripLocation;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripRoutePlan;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripSchedule;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.VehicleId;
import com.trakto.traktoroute.trip.interfaces.rest.resources.requests.CreateTripRequest;

public class CreateTripCommandFromRequestAssembler {

    private CreateTripCommandFromRequestAssembler() {}

    public static CreateTripCommand toCommand(CreateTripRequest request) {

        var driverId = new DriverId(request.driverId());

        var vehicleId = new VehicleId(request.vehicleId());

        var origin = new TripLocation(
                request.originAddress(),
                request.originLatitude(),
                request.originLongitude());

        var destination = new TripLocation(
                request.destinationAddress(),
                request.destinationLatitude(),
                request.destinationLongitude());

        var schedule = new TripSchedule(
                request.scheduledAt(),
                null,
                null,
                null);

        var routePlan = new TripRoutePlan(
                request.distanceKm(),
                request.durationMinutes(),
                request.routeReference(),
                request.calculatedAt());

        return new CreateTripCommand(driverId,
                                    vehicleId,
                                    origin,
                                    destination,
                                    schedule,
                                    routePlan);
    }
}