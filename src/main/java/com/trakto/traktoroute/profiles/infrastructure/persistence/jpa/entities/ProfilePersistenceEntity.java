package com.trakto.traktoroute.profiles.infrastructure.persistence.jpa.entities;

import com.trakto.traktoroute.profiles.infrastructure.persistence.jpa.converters.ProfileIdConverter;
import com.trakto.traktoroute.profiles.infrastructure.persistence.jpa.converters.UserReferenceIdConverter;
import com.trakto.traktoroute.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.ProfileId;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.UserReferenceId;
import com.trakto.traktoroute.profiles.infrastructure.persistence.jpa.embeddables.PersonNamePersistenceEmbeddable;

import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.converters.TripIdPersistenceConverter;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "profiles")
@Getter
@Setter
@NoArgsConstructor
public class ProfilePersistenceEntity
        extends AuditableAbstractPersistenceEntity {

    @Convert(converter = ProfileIdConverter.class)
    @Column(
            name = "profile_id",
            nullable = false,
            unique = true,
            updatable = false
    )
    private ProfileId profileId;

    @Convert(converter = UserReferenceIdConverter.class)
    @Column(
            name = "user_reference_id",
            nullable = false,
            unique = true,
            updatable = false
    )
    private UserReferenceId userReferenceId;

    @Embedded
    private PersonNamePersistenceEmbeddable personName;
}