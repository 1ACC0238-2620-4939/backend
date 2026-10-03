package com.trakto.traktoroute.profiles.interfaces.rest.transform;

import com.trakto.traktoroute.profiles.domain.model.aggregates.Profile;
import com.trakto.traktoroute.profiles.interfaces.rest.resources.responses.ProfileResponse;

public final class ProfileResponseFromEntityAssembler {

    private ProfileResponseFromEntityAssembler() {
    }

    public static ProfileResponse toResourceFromEntity(
            Profile profile
    ) {
        var personName = profile.getPersonName();

        return new ProfileResponse(
                profile.getProfileId().value(),
                profile.getUserReferenceId().value(),
                personName.givenNames(),
                personName.paternalSurname(),
                personName.maternalSurname()
        );
    }
}