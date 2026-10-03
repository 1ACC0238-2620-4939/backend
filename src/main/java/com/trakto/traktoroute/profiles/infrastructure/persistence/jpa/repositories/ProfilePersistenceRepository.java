package com.trakto.traktoroute.profiles.infrastructure.persistence.jpa.repositories;

import com.trakto.traktoroute.profiles.domain.model.valueobjects.ProfileId;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.UserReferenceId;
import com.trakto.traktoroute.profiles.infrastructure.persistence.jpa.entities.ProfilePersistenceEntity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfilePersistenceRepository extends JpaRepository<ProfilePersistenceEntity, Long> {

    Optional<ProfilePersistenceEntity> findByProfileId(ProfileId profileId);

    Optional<ProfilePersistenceEntity> findByUserReferenceId(UserReferenceId userReferenceId);

    boolean existsByProfileId(ProfileId profileId);

    boolean existsByUserReferenceId(UserReferenceId userReferenceId);
}