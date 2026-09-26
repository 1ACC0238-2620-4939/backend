package com.trakto.traktoroute.trip.interfaces.rest.controllers;

import com.trakto.traktoroute.trip.application.queries.trips.GetAllTripsQuery;
import com.trakto.traktoroute.trip.application.queries.stops.GetStopByTripIdAndStopIdQuery;
import com.trakto.traktoroute.trip.application.queries.stops.GetStopsByTripIdQuery;
import com.trakto.traktoroute.trip.application.queries.trips.GetTripByIdQuery;
import com.trakto.traktoroute.trip.application.queries.trips.GetTripsByStatusQuery;
import com.trakto.traktoroute.trip.application.services.TripQueryService;
import com.trakto.traktoroute.trip.domain.model.aggregates.Trip;
import com.trakto.traktoroute.trip.domain.model.entities.TripStop;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;
import com.trakto.traktoroute.trip.domain.model.enums.TripStatus;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopId;
import com.trakto.traktoroute.trip.interfaces.rest.resources.responses.TripResponse;
import com.trakto.traktoroute.trip.interfaces.rest.resources.responses.TripStopResponse;
import com.trakto.traktoroute.trip.interfaces.rest.transform.TripResponseFromEntityAssembler;
import com.trakto.traktoroute.trip.interfaces.rest.transform.TripStopResponseFromEntityAssembler;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/trips")
public class TripQueryController {

    private final TripQueryService tripQueryService;

    public TripQueryController(TripQueryService tripQueryService) {
        this.tripQueryService = tripQueryService;
    }

    // GET ALL TRIPS
    @GetMapping
    @Operation(summary = "Get all trips",
            description = "Returns all registered trips")
    public ResponseEntity<List<TripResponse>> getAllTrips() {

        var trips = tripQueryService.handle(new GetAllTripsQuery());

        var responses = trips.stream()
                .map(TripResponseFromEntityAssembler::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    // GET TRIP BY ID
    @GetMapping("/{tripId}")
    @Operation(summary = "Get trip by ID",
            description = "Returns a specific trip using its unique identifier")
    public ResponseEntity<TripResponse> getTripById(
            @PathVariable String tripId) {

        var query = new GetTripByIdQuery(
                new TripId(UUID.fromString(tripId))
        );

        Trip trip = tripQueryService.handle(query);

        return ResponseEntity.ok(
                TripResponseFromEntityAssembler.toResponse(trip));
    }

    // GET TRIPS BY STATUS
    @GetMapping("/status/{status}")
    @Operation(summary = "Get trips by status",
            description = "Returns all trips that match the specified status")
    public ResponseEntity<List<TripResponse>> getTripsByStatus(
            @PathVariable String status) {

        var query = new GetTripsByStatusQuery(
                TripStatus.valueOf(status.toUpperCase())
        );

        var trips = tripQueryService.handle(query);

        var responses = trips.stream()
                .map(TripResponseFromEntityAssembler::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    // GET ALL STOPS FROM A TRIP
    @GetMapping("/{tripId}/stops")
    @Operation(summary = "Get all stops from a trip",
            description = "Returns all stops registered for a specific trip")
    public ResponseEntity<List<TripStopResponse>> getStopsByTripId(
            @PathVariable String tripId) {

        var query = new GetStopsByTripIdQuery(
                new TripId(UUID.fromString(tripId))
        );

        List<TripStop> stops = tripQueryService.handle(query);

        var responses = stops.stream()
                .map(TripStopResponseFromEntityAssembler::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    // GET ONE STOP FROM A TRIP
    @GetMapping("/{tripId}/stops/{stopId}")
    @Operation(summary = "Get stop by ID",
            description = "Returns a specific stop belonging to a specific trip")
    public ResponseEntity<TripStopResponse> getStopById(
            @PathVariable String tripId,
            @PathVariable String stopId
    ) {

        var query = new GetStopByTripIdAndStopIdQuery(
                new TripId(UUID.fromString(tripId)),
                new StopId(UUID.fromString(stopId))
        );

        TripStop stop = tripQueryService.handle(query);

        return ResponseEntity.ok(
                TripStopResponseFromEntityAssembler.toResponse(stop)
        );
    }
}