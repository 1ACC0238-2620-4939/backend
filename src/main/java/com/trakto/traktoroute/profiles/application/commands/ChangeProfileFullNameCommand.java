package com.trakto.traktoroute.profiles.application.commands;

import com.trakto.traktoroute.profiles.domain.model.valueobjects.PersonName;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.ProfileId;

import java.util.Objects;

public record ChangeProfileFullNameCommand(
        ProfileId profileId,
        PersonName personName
) {

    public ChangeProfileFullNameCommand {
        Objects.requireNonNull(
                profileId,
                "Profile ID cannot be null"
        );

        Objects.requireNonNull(
                personName,
                "Person name cannot be null"
        );
    }
}