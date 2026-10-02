package com.trakto.traktoroute.fleet.interfaces.rest.controllers.driver;

import com.trakto.traktoroute.fleet.application.queries.driver.GetAllDriversQuery;
import com.trakto.traktoroute.fleet.application.queries.driver.GetDriverByIdQuery;
import com.trakto.traktoroute.fleet.application.queries.driver.GetDriverByProfileIdQuery;
import com.trakto.traktoroute.fleet.application.queries.driver.GetDriversByStatusQuery;
import com.trakto.traktoroute.fleet.application.services.driver.DriverQueryService;
import com.trakto.traktoroute.fleet.domain.model.enums.DriverStatus;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.DriverId;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.ProfileId;
import com.trakto.traktoroute.fleet.interfaces.rest.resources.responses.driver.DriverResponse;
import com.trakto.traktoroute.fleet.interfaces.rest.transform.driver.entities.DriverResponseFromEntityAssembler;
import com.trakto.traktoroute.shared.application.result.ApplicationError;
import com.trakto.traktoroute.shared.interfaces.rest.transform.ErrorResponseAssembler;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/drivers")
public class DriverQueryController {

    private final DriverQueryService driverQueryService;

    public DriverQueryController(DriverQueryService driverQueryService) {
        this.driverQueryService = driverQueryService;
    }

    @GetMapping
    @Operation(summary = "Get all drivers")
    public ResponseEntity<List<DriverResponse>> getAllDrivers() {

        var drivers = driverQueryService.handle(new GetAllDriversQuery());

        var responses = drivers.stream()
                .map(DriverResponseFromEntityAssembler::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{driverId}")
    @Operation(summary = "Get driver by ID")
    public ResponseEntity<?> getDriverById(
            @PathVariable("driverId") UUID driverId) {

        var query = new GetDriverByIdQuery(new DriverId(driverId));
        var driverOptional = driverQueryService.handle(query);

        if (driverOptional.isEmpty()) {
            return ErrorResponseAssembler.toErrorResponseFromApplicationError(
                    ApplicationError.notFound(
                            "Driver",
                            driverId.toString()
                    )
            );
        }
        var responses = DriverResponseFromEntityAssembler.toResponse(driverOptional.get());
        return ResponseEntity.ok(responses);

    }

    @GetMapping("/profile/{profileId}")
    @Operation(summary = "Get driver by profile ID")
    public ResponseEntity<?> getDriverByProfileId(
            @PathVariable("profileId") UUID profileId) {

        var query = new GetDriverByProfileIdQuery(new ProfileId(profileId));
        var driverOptional = driverQueryService.handle(query);

        if (driverOptional.isEmpty()) {
            return ErrorResponseAssembler.toErrorResponseFromApplicationError(
                    ApplicationError.notFound(
                            "Driver",
                            "profileId=" + profileId
                    )
            );
        }

        return ResponseEntity.ok(
                DriverResponseFromEntityAssembler.toResponse(
                        driverOptional.get()
                )
        );
    }

    @GetMapping("/status/{status}")
    @Operation(summary = "Get drivers by status")
    public ResponseEntity<List<DriverResponse>> getDriversByStatus(
            @PathVariable("status") DriverStatus status) {

        var query = new GetDriversByStatusQuery(status);
        var drivers = driverQueryService.handle(query);

        var responses = drivers.stream()
                .map(DriverResponseFromEntityAssembler::toResponse)
                .toList();

        return ResponseEntity.ok(responses);
    }
}