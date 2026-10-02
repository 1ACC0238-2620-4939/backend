package com.trakto.traktoroute.fleet.interfaces.rest.transform.driver.request;

import com.trakto.traktoroute.fleet.application.commands.driver.ChangeDriverLicenseNumberCommand;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.DriverId;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.LicenseNumber;
import com.trakto.traktoroute.fleet.interfaces.rest.resources.requests.driver.ChangeDriverLicenseNumberRequest;

import java.util.UUID;

public final class ChangeDriverLicenseNumberCommandFromRequestAssembler {

    private ChangeDriverLicenseNumberCommandFromRequestAssembler() {
    }

    public static ChangeDriverLicenseNumberCommand toCommand(
            UUID driverId,
            ChangeDriverLicenseNumberRequest request) {

        return new ChangeDriverLicenseNumberCommand(
                new DriverId(driverId),
                new LicenseNumber(request.licenseNumber())
        );
    }
}