package com.trakto.traktoroute.tracking.interfaces.rest.controllers;

import com.trakto.traktoroute.shared.interfaces.rest.transform.ResponseEntityAssembler;
import com.trakto.traktoroute.tracking.application.commands.FinishTrackingCommand;
import com.trakto.traktoroute.tracking.application.services.TrackingCommandService;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TrackingId;
import com.trakto.traktoroute.tracking.interfaces.rest.resources.requests.ChangeStopReasonRequest;
import com.trakto.traktoroute.tracking.interfaces.rest.resources.requests.CreateTrackingRequest;
import com.trakto.traktoroute.tracking.interfaces.rest.resources.requests.RegisterPositionRequest;
import com.trakto.traktoroute.tracking.interfaces.rest.transform.ChangeStopReasonCommandFromRequestAssembler;
import com.trakto.traktoroute.tracking.interfaces.rest.transform.CreateTrackingCommandFromRequestAssembler;
import com.trakto.traktoroute.tracking.interfaces.rest.transform.RegisterPositionCommandFromRequestAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/trackings")
@Tag(name = "Tracking - Command",
        description = "Tracking management")
public class TrackingCommandController {

    private final TrackingCommandService trackingCommandService;

    public TrackingCommandController(
            TrackingCommandService trackingCommandService
    ) {
        this.trackingCommandService = trackingCommandService;
    }

    @PostMapping
    @Operation(
            summary = "Create tracking",
            description = "Creates tracking for a trip"
    )
    public ResponseEntity<?> createTracking(
            @Valid @RequestBody CreateTrackingRequest request
    ) {
        var command =
                CreateTrackingCommandFromRequestAssembler.toCommand(request);

        var result = trackingCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                tracking -> Map.of(
                        "trackingId",
                        tracking.getTrackingId().value()
                ),
                HttpStatus.CREATED
        );
    }

    @PostMapping("/{trackingId}/positions")
    @Operation(
            summary = "Register a GPS position",
            description = "Registers a position and evaluates automatic stops"
    )
    public ResponseEntity<?> registerPosition(
            @PathVariable("trackingId") UUID trackingId,
            @Valid @RequestBody RegisterPositionRequest request
    ) {
        var command =
                RegisterPositionCommandFromRequestAssembler.toCommand(
                        trackingId,
                        request
                );

        var result = trackingCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                ignored -> null,
                HttpStatus.NO_CONTENT
        );
    }

    @PatchMapping("/{trackingId}/stops/{stopId}/reason")
    @Operation(
            summary = "Change a stop reason",
            description = "Changes the reason of a stop belonging to the tracking"
    )
    public ResponseEntity<?> changeStopReason(
            @PathVariable("trackingId") UUID trackingId,
            @PathVariable("stopId") UUID stopId,
            @Valid @RequestBody ChangeStopReasonRequest request
    ) {
        var command =
                ChangeStopReasonCommandFromRequestAssembler.toCommand(
                        trackingId,
                        stopId,
                        request
                );

        var result = trackingCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                ignored -> null,
                HttpStatus.NO_CONTENT
        );
    }

    @PatchMapping("/{trackingId}/finish")
    @Operation(
            summary = "Finish tracking",
            description = "Finishes tracking and closes any open stop"
    )
    public ResponseEntity<?> finishTracking(
            @PathVariable("trackingId") UUID trackingId
    ) {
        var command = new FinishTrackingCommand(
                new TrackingId(trackingId)
        );

        var result = trackingCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                ignored -> null,
                HttpStatus.NO_CONTENT
        );
    }
}