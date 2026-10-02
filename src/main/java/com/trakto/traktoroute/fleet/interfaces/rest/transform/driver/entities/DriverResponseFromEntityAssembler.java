package com.trakto.traktoroute.fleet.interfaces.rest.transform.driver.entities;

import com.trakto.traktoroute.fleet.domain.model.aggregates.Driver;
import com.trakto.traktoroute.fleet.interfaces.rest.resources.responses.driver.DriverResponse;

public final class DriverResponseFromEntityAssembler {

    private DriverResponseFromEntityAssembler() {
    }

    public static DriverResponse toResponse(Driver driver) {
        return new DriverResponse(
                driver.getId().value(),
                driver.getProfileId().value(),
                driver.getLicenseNumber().value(),
                driver.getStatus().name()
        );
    }
}