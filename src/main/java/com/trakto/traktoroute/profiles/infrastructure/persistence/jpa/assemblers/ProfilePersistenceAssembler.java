package com.trakto.traktoroute.profiles.infrastructure.persistence.jpa.assemblers;

import com.trakto.traktoroute.profiles.domain.model.aggregates.Profile;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.PersonName;
import com.trakto.traktoroute.profiles.infrastructure.persistence.jpa.embeddables.PersonNamePersistenceEmbeddable;
import com.trakto.traktoroute.profiles.infrastructure.persistence.jpa.entities.ProfilePersistenceEntity;

import java.util.Objects;

public final class ProfilePersistenceAssembler {

    private ProfilePersistenceAssembler() {
    }

    public static Profile toDomainFromPersistence(
            ProfilePersistenceEntity entity
    ) {
        Objects.requireNonNull(
                entity,
                "Profile persistence entity cannot be null"
        );

        PersonNamePersistenceEmbeddable personName =
                Objects.requireNonNull(
                        entity.getPersonName(),
                        "Persisted person name cannot be null"
                );

        return Profile.reconstitute(
                entity.getProfileId(),
                entity.getUserReferenceId(),
                new PersonName(
                        personName.getGivenNames(),
                        personName.getPaternalSurname(),
                        personName.getMaternalSurname()
                )
        );
    }

    public static ProfilePersistenceEntity toPersistenceFromDomain(
            Profile profile
    ) {
        ProfilePersistenceEntity entity =
                new ProfilePersistenceEntity();

        updatePersistenceFromDomain(profile, entity);

        return entity;
    }

    public static void updatePersistenceFromDomain(
            Profile profile,
            ProfilePersistenceEntity entity
    ) {
        Objects.requireNonNull(
                profile,
                "Profile cannot be null"
        );

        Objects.requireNonNull(
                entity,
                "Profile persistence entity cannot be null"
        );

        entity.setProfileId(profile.getProfileId());
        entity.setUserReferenceId(profile.getUserReferenceId());

        PersonName personName = profile.getPersonName();

        entity.setPersonName(
                new PersonNamePersistenceEmbeddable(
                        personName.givenNames(),
                        personName.paternalSurname(),
                        personName.maternalSurname()
                )
        );
    }
}