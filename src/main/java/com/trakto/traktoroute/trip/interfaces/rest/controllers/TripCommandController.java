package com.trakto.traktoroute.trip.interfaces.rest.controllers;

import com.trakto.traktoroute.shared.interfaces.rest.transform.ResponseEntityAssembler;
import com.trakto.traktoroute.trip.application.commands.stops.FinishTripStopCommand;
import com.trakto.traktoroute.trip.application.commands.stops.StartTripStopCommand;
import com.trakto.traktoroute.trip.application.commands.trips.CancelTripCommand;
import com.trakto.traktoroute.trip.application.commands.trips.CompleteTripCommand;
import com.trakto.traktoroute.trip.application.commands.trips.StartTripCommand;
import com.trakto.traktoroute.trip.application.services.TripCommandService;
import com.trakto.traktoroute.trip.domain.model.enums.StopReason;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopId;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopLocation;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.TripInstant;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;
import com.trakto.traktoroute.trip.interfaces.rest.resources.requests.CancelTripRequest;
import com.trakto.traktoroute.trip.interfaces.rest.resources.requests.CompleteTripRequest;
import com.trakto.traktoroute.trip.interfaces.rest.resources.requests.CreateTripRequest;
import com.trakto.traktoroute.trip.interfaces.rest.resources.requests.FinishTripStopRequest;
import com.trakto.traktoroute.trip.interfaces.rest.resources.requests.StartTripRequest;
import com.trakto.traktoroute.trip.interfaces.rest.resources.requests.StartTripStopRequest;
import com.trakto.traktoroute.trip.interfaces.rest.transform.CreateTripCommandFromRequestAssembler;
import com.trakto.traktoroute.trip.interfaces.rest.transform.TripResponseFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/trips")
public class TripCommandController {

    private final TripCommandService tripCommandService;

    public TripCommandController(
            TripCommandService tripCommandService) {
        this.tripCommandService = tripCommandService;
    }

    // CREATE TRIP
    @PostMapping
    @Operation(summary = "Create a trip",
            description = "Creates a new trip with the provided trip information")
    public ResponseEntity<?> createTrip(
            @RequestBody CreateTripRequest request
    ) {

        var command =
                CreateTripCommandFromRequestAssembler.toCommand(request);

        var result = tripCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                TripResponseFromEntityAssembler::toResponse,
                HttpStatus.CREATED);
    }

    // START TRIP
    @Operation(summary = "Start a trip",
            description = "Starts a scheduled trip using its unique identifier")
    @PatchMapping("/{tripId}/start")
    public ResponseEntity<?> startTrip(
            @PathVariable UUID tripId,
            @RequestBody StartTripRequest request
    ) {

        var command = new StartTripCommand(
                new TripId(tripId),
                request.startedAt()
        );

        var result = tripCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                TripResponseFromEntityAssembler::toResponse,
                HttpStatus.OK);
    }

    // COMPLETE TRIP
    @PatchMapping("/{tripId}/complete")
    @Operation(summary = "Complete a trip",
            description = "Marks an in-progress trip as completed")
    public ResponseEntity<?> completeTrip(
            @PathVariable UUID tripId,
            @RequestBody CompleteTripRequest request
    ) {

        var command = new CompleteTripCommand(
                new TripId(tripId),
                request.completedAt()
        );

        var result = tripCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                TripResponseFromEntityAssembler::toResponse,
                HttpStatus.OK);
    }

    // CANCEL TRIP
    @PatchMapping("/{tripId}/cancel")
    @Operation(summary = "Cancel a trip",
            description = "Cancels a trip using its unique identifier")
    public ResponseEntity<?> cancelTrip(
            @PathVariable UUID tripId,
            @RequestBody CancelTripRequest request
    ) {

        var command = new CancelTripCommand(
                new TripId(tripId),
                request.cancelledAt()
        );

        var result = tripCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                TripResponseFromEntityAssembler::toResponse,
                HttpStatus.OK);
    }

    // START TRIP STOP
    @PostMapping("/{tripId}/stops")
    @Operation(summary = "Start a trip stop",
            description = "Registers a new stop for an active trip")
    public ResponseEntity<?> startStop(
            @PathVariable UUID tripId,
            @RequestBody StartTripStopRequest request
    ) {

        var command = new StartTripStopCommand(
                new TripId(tripId),
                new StopLocation(request.latitude(), request.longitude()),
                new TripInstant(request.startedAt()),
                StopReason.valueOf(request.reason())
        );

        var result = tripCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                TripResponseFromEntityAssembler::toResponse,
                HttpStatus.OK);
    }

    // FINISH TRIP STOP
    @PatchMapping("/{tripId}/stops/{stopId}/finish")
    @Operation(summary = "Finish a trip stop",
            description = "Finishes an active stop belonging to a specific trip")
    public ResponseEntity<?> finishStop(
            @PathVariable UUID tripId,
            @PathVariable UUID stopId,
            @RequestBody FinishTripStopRequest request
    ) {

        var command = new FinishTripStopCommand(
                new TripId(tripId),
                new StopId(stopId),
                new TripInstant(request.endedAt())
        );

        var result = tripCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                TripResponseFromEntityAssembler::toResponse,
                HttpStatus.OK);
    }
}