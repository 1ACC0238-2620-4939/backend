package com.trakto.traktoroute.fleet.interfaces.rest.controllers.vehicle;

import com.trakto.traktoroute.fleet.application.commands.vehicle.ChangeVehicleCapacityCommand;
import com.trakto.traktoroute.fleet.application.commands.vehicle.ChangeVehiclePlateNumberCommand;
import com.trakto.traktoroute.fleet.application.commands.vehicle.CreateVehicleCommand;
import com.trakto.traktoroute.fleet.application.services.vehicle.VehicleCommandService;
import com.trakto.traktoroute.fleet.interfaces.rest.resources.requests.vehicle.ChangeVehicleCapacityRequest;
import com.trakto.traktoroute.fleet.interfaces.rest.resources.requests.vehicle.ChangeVehiclePlateNumberRequest;
import com.trakto.traktoroute.fleet.interfaces.rest.resources.requests.vehicle.CreateVehicleRequest;
import com.trakto.traktoroute.fleet.interfaces.rest.transform.vehicle.path.ActivateVehicleCommandFromPathAssembler;
import com.trakto.traktoroute.fleet.interfaces.rest.transform.vehicle.request.ChangeVehicleCapacityCommandFromRequestAssembler;
import com.trakto.traktoroute.fleet.interfaces.rest.transform.vehicle.request.ChangeVehiclePlateNumberCommandFromRequestAssembler;
import com.trakto.traktoroute.fleet.interfaces.rest.transform.vehicle.request.CreateVehicleCommandFromRequestAssembler;
import com.trakto.traktoroute.fleet.interfaces.rest.transform.vehicle.path.DeactivateVehicleCommandFromPathAssembler;
import com.trakto.traktoroute.fleet.interfaces.rest.transform.vehicle.entities.VehicleResponseFromEntityAssembler;
import com.trakto.traktoroute.shared.application.result.ApplicationError;
import com.trakto.traktoroute.shared.interfaces.rest.transform.ErrorResponseAssembler;
import com.trakto.traktoroute.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/vehicles")
public class VehicleCommandController {

    private final VehicleCommandService vehicleCommandService;

    public VehicleCommandController(
            VehicleCommandService vehicleCommandService) {
        this.vehicleCommandService = vehicleCommandService;
    }

    @PostMapping
    @Operation(summary = "Create a vehicle")
    public ResponseEntity<?> createVehicle(
            @RequestBody CreateVehicleRequest request) {

        CreateVehicleCommand command;

        try {
            command = CreateVehicleCommandFromRequestAssembler.toCommand(request);
        } catch (IllegalArgumentException | NullPointerException e) {
            return ErrorResponseAssembler.toErrorResponseFromApplicationError(
                    ApplicationError.validationError(
                            "Vehicle",
                            e.getMessage()
                    )
            );
        }

        var result = vehicleCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                VehicleResponseFromEntityAssembler::toResponse,
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{vehicleId}/plate-number")
    @Operation(summary = "Change a vehicle plate number")
    public ResponseEntity<?> changePlateNumber(
            @PathVariable("vehicleId") UUID vehicleId,
            @RequestBody ChangeVehiclePlateNumberRequest request) {

        ChangeVehiclePlateNumberCommand command;

        try {
            command =
                    ChangeVehiclePlateNumberCommandFromRequestAssembler.toCommand(
                            vehicleId,
                            request
                    );
        } catch (IllegalArgumentException | NullPointerException e) {
            return ErrorResponseAssembler.toErrorResponseFromApplicationError(
                    ApplicationError.validationError(
                            "PlateNumber",
                            e.getMessage()
                    )
            );
        }

        var result = vehicleCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                VehicleResponseFromEntityAssembler::toResponse,
                HttpStatus.OK
        );
    }

    @PatchMapping("/{vehicleId}/capacity")
    @Operation(summary = "Change a vehicle capacity")
    public ResponseEntity<?> changeCapacity(
            @PathVariable("vehicleId") UUID vehicleId,
            @RequestBody ChangeVehicleCapacityRequest request) {

        ChangeVehicleCapacityCommand command;

        try {
            command =
                    ChangeVehicleCapacityCommandFromRequestAssembler.toCommand(
                            vehicleId,
                            request
                    );
        } catch (IllegalArgumentException | NullPointerException e) {
            return ErrorResponseAssembler.toErrorResponseFromApplicationError(
                    ApplicationError.validationError(
                            "VehicleCapacity",
                            e.getMessage()
                    )
            );
        }

        var result = vehicleCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                VehicleResponseFromEntityAssembler::toResponse,
                HttpStatus.OK
        );
    }

    @PatchMapping("/{vehicleId}/activate")
    @Operation(summary = "Activate a vehicle")
    public ResponseEntity<?> activateVehicle(
            @PathVariable("vehicleId") UUID vehicleId) {

        var command =
                ActivateVehicleCommandFromPathAssembler.toCommand(vehicleId);

        var result = vehicleCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                VehicleResponseFromEntityAssembler::toResponse,
                HttpStatus.OK
        );
    }

    @PatchMapping("/{vehicleId}/deactivate")
    @Operation(summary = "Deactivate a vehicle")
    public ResponseEntity<?> deactivateVehicle(
            @PathVariable("vehicleId") UUID vehicleId) {

        var command =
                DeactivateVehicleCommandFromPathAssembler.toCommand(vehicleId);

        var result = vehicleCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                VehicleResponseFromEntityAssembler::toResponse,
                HttpStatus.OK
        );
    }
}