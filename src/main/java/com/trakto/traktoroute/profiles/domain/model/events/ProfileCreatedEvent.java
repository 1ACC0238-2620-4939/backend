package com.trakto.traktoroute.profiles.domain.model.events;

import com.trakto.traktoroute.profiles.domain.model.valueobjects.PersonName;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.ProfileId;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.UserReferenceId;

import java.time.Instant;

public record ProfileCreatedEvent(
        ProfileId profileId,
        UserReferenceId userReferenceId,
        PersonName personName,
        Instant occurredAt
) {
}