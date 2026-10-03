package com.trakto.traktoroute.profiles.domain.model.events;

import com.trakto.traktoroute.profiles.domain.model.valueobjects.PersonName;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.ProfileId;

import java.time.Instant;

public record ProfileFullNameChangedEvent(
        ProfileId profileId,
        PersonName previousPersonName,
        PersonName personName,
        Instant occurredAt
) {
}