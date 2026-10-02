package com.trakto.traktoroute.fleet.interfaces.rest.controllers.driver;

import com.trakto.traktoroute.fleet.application.commands.driver.ChangeDriverLicenseNumberCommand;
import com.trakto.traktoroute.fleet.application.commands.driver.CreateDriverCommand;
import com.trakto.traktoroute.fleet.application.services.driver.DriverCommandService;
import com.trakto.traktoroute.fleet.interfaces.rest.resources.requests.driver.ChangeDriverLicenseNumberRequest;
import com.trakto.traktoroute.fleet.interfaces.rest.resources.requests.driver.CreateDriverRequest;
import com.trakto.traktoroute.fleet.interfaces.rest.transform.driver.path.ActivateDriverCommandFromPathAssembler;
import com.trakto.traktoroute.fleet.interfaces.rest.transform.driver.request.ChangeDriverLicenseNumberCommandFromRequestAssembler;
import com.trakto.traktoroute.fleet.interfaces.rest.transform.driver.request.CreateDriverCommandFromRequestAssembler;
import com.trakto.traktoroute.fleet.interfaces.rest.transform.driver.path.DeactivateDriverCommandFromPathAssembler;
import com.trakto.traktoroute.fleet.interfaces.rest.transform.driver.entities.DriverResponseFromEntityAssembler;
import com.trakto.traktoroute.shared.application.result.ApplicationError;
import com.trakto.traktoroute.shared.interfaces.rest.transform.ErrorResponseAssembler;
import com.trakto.traktoroute.shared.interfaces.rest.transform.ResponseEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/drivers")
public class DriverCommandController {

    private final DriverCommandService driverCommandService;

    public DriverCommandController(DriverCommandService driverCommandService) {
        this.driverCommandService = driverCommandService;
    }

    @PostMapping
    @Operation(summary = "Create a driver")
    public ResponseEntity<?> createDriver(@RequestBody CreateDriverRequest request) {

        CreateDriverCommand command;

        try {
            command = CreateDriverCommandFromRequestAssembler.toCommand(request);
        } catch (IllegalArgumentException | NullPointerException e) {
            return ErrorResponseAssembler.toErrorResponseFromApplicationError(
                    ApplicationError.validationError("Driver", e.getMessage())
            );
        }

        var result = driverCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                DriverResponseFromEntityAssembler::toResponse,
                HttpStatus.CREATED
        );
    }

    @PatchMapping("/{driverId}/license-number")
    @Operation(summary = "Change a driver license number")
    public ResponseEntity<?> changeLicenseNumber(@PathVariable("driverId") UUID driverId,
                                                @RequestBody ChangeDriverLicenseNumberRequest request) {

            ChangeDriverLicenseNumberCommand command;

        try {
            command = ChangeDriverLicenseNumberCommandFromRequestAssembler.toCommand(
                        driverId,
                        request);
        } catch (IllegalArgumentException | NullPointerException e) {
            return ErrorResponseAssembler.toErrorResponseFromApplicationError(
                    ApplicationError.validationError(
                            "LicenseNumber",
                            e.getMessage()
                    )
            );
        }

        var result = driverCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                DriverResponseFromEntityAssembler::toResponse,
                HttpStatus.OK
        );
    }

    @PatchMapping("/{driverId}/activate")
    @Operation(summary = "Activate a driver")
    public ResponseEntity<?> activateDriver(
            @PathVariable("driverId") UUID driverId) {

        var command =
                ActivateDriverCommandFromPathAssembler.toCommand(driverId);

        var result = driverCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                DriverResponseFromEntityAssembler::toResponse,
                HttpStatus.OK
        );
    }

    @PatchMapping("/{driverId}/deactivate")
    @Operation(summary = "Deactivate a driver")
    public ResponseEntity<?> deactivateDriver(
            @PathVariable("driverId") UUID driverId) {

        var command =
                DeactivateDriverCommandFromPathAssembler.toCommand(driverId);

        var result = driverCommandService.handle(command);

        return ResponseEntityAssembler.toResponseEntityFromResult(
                result,
                DriverResponseFromEntityAssembler::toResponse,
                HttpStatus.OK
        );
    }
}