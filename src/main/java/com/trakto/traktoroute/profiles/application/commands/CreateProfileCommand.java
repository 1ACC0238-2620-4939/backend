package com.trakto.traktoroute.profiles.application.commands;

import com.trakto.traktoroute.profiles.domain.model.valueobjects.PersonName;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.UserReferenceId;

import java.util.Objects;

public record CreateProfileCommand(
        UserReferenceId userReferenceId,
        PersonName personName
) {

    public CreateProfileCommand {
        Objects.requireNonNull(
                userReferenceId,
                "User reference ID cannot be null"
        );

        Objects.requireNonNull(
                personName,
                "Person name cannot be null"
        );
    }
}