package com.trakto.traktoroute.trip.interfaces.rest.controllers;

import com.trakto.traktoroute.shared.interfaces.rest.transform.ResponseEntityAssembler;
import com.trakto.traktoroute.trip.application.services.TripCommandService;
import com.trakto.traktoroute.trip.interfaces.rest.resources.requests.CancelTripRequest;
import com.trakto.traktoroute.trip.interfaces.rest.resources.requests.CompleteTripRequest;
import com.trakto.traktoroute.trip.interfaces.rest.resources.requests.CreateTripRequest;
import com.trakto.traktoroute.trip.interfaces.rest.resources.requests.StartTripRequest;
import com.trakto.traktoroute.trip.interfaces.rest.transform.CancelTripCommandFromRequestAssembler;
import com.trakto.traktoroute.trip.interfaces.rest.transform.CompleteTripCommandFromRequestAssembler;
import com.trakto.traktoroute.trip.interfaces.rest.transform.CreateTripCommandFromRequestAssembler;
import com.trakto.traktoroute.trip.interfaces.rest.transform.StartTripCommandFromRequestAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/trips")
@Tag(name = "Trip - Command",
        description = "Trip management")
public class TripCommandController {

    private final TripCommandService tripCommandService;

    public TripCommandController(TripCommandService tripCommandService) {
        this.tripCommandService = tripCommandService;
    }

    @PostMapping
    @Operation(
            summary = "Create a trip",
            description = "Creates a new trip with the provided trip information"
    )
    public ResponseEntity<?> createTrip(
            @RequestBody CreateTripRequest request
    ) {
        var command =
                CreateTripCommandFromRequestAssembler.toCommand(request);

        var result = tripCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                tripId -> Map.of("tripId", tripId.value()),
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{tripId}/start")
    @Operation(
            summary = "Start a trip",
            description = "Starts a scheduled trip using its unique identifier"
    )
    public ResponseEntity<?> startTrip(
            @PathVariable UUID tripId,
            @RequestBody StartTripRequest request
    ) {
        var command =
                StartTripCommandFromRequestAssembler.toCommand(tripId, request);

        var result = tripCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                ignored -> null,
                HttpStatus.NO_CONTENT
        );
    }

    @PatchMapping("/{tripId}/complete")
    @Operation(
            summary = "Complete a trip",
            description = "Marks an in-progress trip as completed"
    )
    public ResponseEntity<?> completeTrip(
            @PathVariable UUID tripId,
            @RequestBody CompleteTripRequest request
    ) {
        var command =
                CompleteTripCommandFromRequestAssembler.toCommand(tripId, request);

        var result = tripCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                ignored -> null,
                HttpStatus.NO_CONTENT
        );
    }

    @PatchMapping("/{tripId}/cancel")
    @Operation(
            summary = "Cancel a trip",
            description = "Cancels a trip using its unique identifier"
    )
    public ResponseEntity<?> cancelTrip(
            @PathVariable UUID tripId,
            @RequestBody CancelTripRequest request
    ) {
        var command =
                CancelTripCommandFromRequestAssembler.toCommand(tripId, request);

        var result = tripCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                ignored -> null,
                HttpStatus.NO_CONTENT
        );
    }
}