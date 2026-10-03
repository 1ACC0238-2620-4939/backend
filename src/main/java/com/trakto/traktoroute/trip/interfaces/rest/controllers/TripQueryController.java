package com.trakto.traktoroute.trip.interfaces.rest.controllers;

import com.trakto.traktoroute.trip.application.queries.GetAllTripsQuery;
import com.trakto.traktoroute.trip.application.queries.GetTripByIdQuery;
import com.trakto.traktoroute.trip.application.queries.GetTripsByStatusQuery;
import com.trakto.traktoroute.trip.application.services.TripQueryService;
import com.trakto.traktoroute.trip.domain.model.enums.TripStatus;
import com.trakto.traktoroute.trip.domain.model.valueobjects.TripId;
import com.trakto.traktoroute.trip.interfaces.rest.resources.responses.TripResponse;
import com.trakto.traktoroute.trip.interfaces.rest.transform.TripResponseFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Locale;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/trips")
@Tag(name = "Trip - Queries",
        description = "Trip management")
public class TripQueryController {

    private final TripQueryService tripQueryService;

    public TripQueryController(TripQueryService tripQueryService) {
        this.tripQueryService = tripQueryService;
    }

    @GetMapping
    @Operation(
            summary = "Get all trips",
            description = "Returns all registered trips"
    )
    public ResponseEntity<List<TripResponse>> getAllTrips() {
        var trips = tripQueryService.handle(new GetAllTripsQuery());

        var responses = trips.stream()
                .map(TripResponseFromEntityAssembler::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{tripId}")
    @Operation(
            summary = "Get trip by ID",
            description = "Returns a specific trip using its unique identifier"
    )
    public ResponseEntity<TripResponse> getTripById(
            @PathVariable UUID tripId
    ) {
        var query = new GetTripByIdQuery(new TripId(tripId));

        var trip = tripQueryService.handle(query);

        return ResponseEntity.ok(
                TripResponseFromEntityAssembler.toResponse(trip)
        );
    }

    @GetMapping("/status/{status}")
    @Operation(
            summary = "Get trips by status",
            description = "Returns all trips that match the specified status"
    )
    public ResponseEntity<List<TripResponse>> getTripsByStatus(
            @PathVariable String status
    ) {
        var query = new GetTripsByStatusQuery(
                TripStatus.valueOf(status.toUpperCase(Locale.ROOT))
        );

        var trips = tripQueryService.handle(query);

        var responses = trips.stream()
                .map(TripResponseFromEntityAssembler::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }
}