package com.trakto.traktoroute.profiles.interfaces.rest.transform;

import com.trakto.traktoroute.profiles.application.commands.ChangeProfileFullNameCommand;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.PersonName;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.ProfileId;
import com.trakto.traktoroute.profiles.interfaces.rest.resources.requests.ChangeProfileFullNameRequest;

import java.util.UUID;

public final class ChangeProfileFullNameCommandFromRequestAssembler {

    private ChangeProfileFullNameCommandFromRequestAssembler() {
    }

    public static ChangeProfileFullNameCommand toCommand(
            UUID profileId,
            ChangeProfileFullNameRequest request
    ) {
        return new ChangeProfileFullNameCommand(
                new ProfileId(profileId),
                new PersonName(
                        request.givenNames(),
                        request.paternalSurname(),
                        request.maternalSurname()
                )
        );
    }
}