package com.trakto.traktoroute.fleet.interfaces.rest.transform.driver.request;

import com.trakto.traktoroute.fleet.application.commands.driver.CreateDriverCommand;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.LicenseNumber;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.ProfileId;
import com.trakto.traktoroute.fleet.interfaces.rest.resources.requests.driver.CreateDriverRequest;

public final class CreateDriverCommandFromRequestAssembler {

    private CreateDriverCommandFromRequestAssembler() {
    }

    public static CreateDriverCommand toCommand(CreateDriverRequest request) {
        return new CreateDriverCommand(
                new ProfileId(request.profileId()),
                new LicenseNumber(request.licenseNumber())
        );
    }
}