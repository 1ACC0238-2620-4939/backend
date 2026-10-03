package com.trakto.traktoroute.fleet.interfaces.rest.controllers.vehicle;

import com.trakto.traktoroute.fleet.application.queries.vehicle.GetAllVehiclesQuery;
import com.trakto.traktoroute.fleet.application.queries.vehicle.GetVehicleByIdQuery;
import com.trakto.traktoroute.fleet.application.queries.vehicle.GetVehiclesByStatusQuery;
import com.trakto.traktoroute.fleet.application.services.vehicle.VehicleQueryService;
import com.trakto.traktoroute.fleet.domain.model.enums.VehicleStatus;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleId;
import com.trakto.traktoroute.fleet.interfaces.rest.resources.responses.vehicle.VehicleResponse;
import com.trakto.traktoroute.fleet.interfaces.rest.transform.vehicle.entities.VehicleResponseFromEntityAssembler;
import com.trakto.traktoroute.shared.application.result.ApplicationError;
import com.trakto.traktoroute.shared.interfaces.rest.transform.ErrorResponseAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/vehicles")
@Tag(name = "Vehicle - Query",
    description = "Fleet management")
public class VehicleQueryController {

    private final VehicleQueryService vehicleQueryService;

    public VehicleQueryController(VehicleQueryService vehicleQueryService) {
        this.vehicleQueryService = vehicleQueryService;
    }

    @GetMapping
    @Operation(summary = "Get all vehicles")
    public ResponseEntity<List<VehicleResponse>> getAllVehicles() {

        var vehicles = vehicleQueryService.handle(new GetAllVehiclesQuery());

        var responses = vehicles.stream()
                .map(VehicleResponseFromEntityAssembler::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{vehicleId}")
    @Operation(summary = "Get vehicle by ID")
    public ResponseEntity<?> getVehicleById(
            @PathVariable("vehicleId") UUID vehicleId) {

        var query = new GetVehicleByIdQuery(new VehicleId(vehicleId));
        var vehicleOptional = vehicleQueryService.handle(query);

        if (vehicleOptional.isEmpty()) {
            return ErrorResponseAssembler.toErrorResponseFromApplicationError(
                    ApplicationError.notFound(
                            "Vehicle",
                            vehicleId.toString()
                    )
            );
        }

        return ResponseEntity.ok(
                VehicleResponseFromEntityAssembler.toResponse(
                        vehicleOptional.get()
                )
        );
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get vehicles by status")
    public ResponseEntity<List<VehicleResponse>> getVehiclesByStatus(
            @PathVariable("status") VehicleStatus status) {

        var query = new GetVehiclesByStatusQuery(status);
        var vehicles = vehicleQueryService.handle(query);

        var responses = vehicles.stream()
                .map(VehicleResponseFromEntityAssembler::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }
}