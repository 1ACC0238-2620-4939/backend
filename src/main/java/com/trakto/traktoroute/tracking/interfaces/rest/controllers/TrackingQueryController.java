package com.trakto.traktoroute.tracking.interfaces.rest.controllers;

import com.trakto.traktoroute.tracking.application.queries.GetLastPositionByTripIdQuery;
import com.trakto.traktoroute.tracking.application.queries.GetStopByTripIdAndStopIdQuery;
import com.trakto.traktoroute.tracking.application.queries.GetStopsByTripIdQuery;
import com.trakto.traktoroute.tracking.application.queries.GetTrackingByIdQuery;
import com.trakto.traktoroute.tracking.application.queries.GetTrackingByTripIdQuery;
import com.trakto.traktoroute.tracking.application.services.TrackingQueryService;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.PositionReport;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.StopId;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TrackingId;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TripReferenceId;
import com.trakto.traktoroute.tracking.interfaces.rest.resources.responses.TrackingResponse;
import com.trakto.traktoroute.tracking.interfaces.rest.resources.responses.TrackingStopResponse;
import com.trakto.traktoroute.tracking.interfaces.rest.transform.TrackingResponseFromEntityAssembler;
import com.trakto.traktoroute.tracking.interfaces.rest.transform.TrackingStopResponseFromEntityAssembler;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/trackings")
@Tag(name = "Tracking - Query",
        description = "Tracking information queries")
public class TrackingQueryController {

    private final TrackingQueryService trackingQueryService;

    public TrackingQueryController(
            TrackingQueryService trackingQueryService
    ) {
        this.trackingQueryService = trackingQueryService;
    }

    @GetMapping("/{trackingId}")
    @Operation(
            summary = "Get tracking by ID",
            description = "Returns tracking information using its unique identifier"
    )
    public ResponseEntity<TrackingResponse> getTrackingById(
            @PathVariable("trackingId") UUID trackingId
    ) {
        var query = new GetTrackingByIdQuery(
                new TrackingId(trackingId)
        );

        var tracking = trackingQueryService.handle(query);

        var response =
                TrackingResponseFromEntityAssembler.toResourceFromEntity(
                        tracking
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/trips/{tripId}")
    @Operation(
            summary = "Get tracking by trip ID",
            description = "Returns the tracking associated with a trip"
    )
    public ResponseEntity<TrackingResponse> getTrackingByTripId(
            @PathVariable("tripId") UUID tripId
    ) {
        var query = new GetTrackingByTripIdQuery(
                new TripReferenceId(tripId)
        );

        var tracking = trackingQueryService.handle(query);

        var response =
                TrackingResponseFromEntityAssembler.toResourceFromEntity(
                        tracking
                );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/trips/{tripId}/last-position")
    @Operation(
            summary = "Get the last GPS position of a trip",
            description = "Returns 204 when tracking exists but has no GPS reports"
    )
    public ResponseEntity<PositionReport> getLastPositionByTripId(
            @PathVariable("tripId") UUID tripId
    ) {
        var query = new GetLastPositionByTripIdQuery(
                new TripReferenceId(tripId)
        );

        var positionOptional = trackingQueryService.handle(query);

        if (positionOptional.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(positionOptional.get());
    }

    @GetMapping("/trips/{tripId}/stops")
    @Operation(
            summary = "Get stops by trip ID",
            description = "Returns the automatically detected stops of a trip"
    )
    public ResponseEntity<List<TrackingStopResponse>> getStopsByTripId(
            @PathVariable("tripId") UUID tripId
    ) {
        var query = new GetStopsByTripIdQuery(
                new TripReferenceId(tripId)
        );

        var stops = trackingQueryService.handle(query);

        var response = stops.stream()
                .map(TrackingStopResponseFromEntityAssembler::toResourceFromEntity)
                .toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/trips/{tripId}/stops/{stopId}")
    @Operation(
            summary = "Get a stop by trip ID and stop ID",
            description = "Returns a specific stop belonging to the trip"
    )
    public ResponseEntity<TrackingStopResponse> getStopByTripIdAndStopId(
            @PathVariable("tripId") UUID tripId,
            @PathVariable("stopId") UUID stopId
    ) {
        var query = new GetStopByTripIdAndStopIdQuery(
                new TripReferenceId(tripId),
                new StopId(stopId)
        );

        var stop = trackingQueryService.handle(query);

        var response =
                TrackingStopResponseFromEntityAssembler.toResourceFromEntity(
                        stop
                );

        return ResponseEntity.ok(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleNotFound(
            IllegalArgumentException exception
    ) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of(
                        "message",
                        exception.getMessage()
                ));
    }
}