package com.trakto.traktoroute.profiles.interfaces.rest.transform;

import com.trakto.traktoroute.profiles.application.commands.CreateProfileCommand;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.PersonName;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.UserReferenceId;
import com.trakto.traktoroute.profiles.interfaces.rest.resources.requests.CreateProfileRequest;

public final class CreateProfileCommandFromRequestAssembler {

    private CreateProfileCommandFromRequestAssembler() {
    }

    public static CreateProfileCommand toCommand(
            CreateProfileRequest request
    ) {
        return new CreateProfileCommand(
                new UserReferenceId(request.userReferenceId()),
                new PersonName(
                        request.givenNames(),
                        request.paternalSurname(),
                        request.maternalSurname()
                )
        );
    }
}