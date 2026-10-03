package com.trakto.traktoroute.profiles.infrastructure.persistence.jpa.adapters;

import com.trakto.traktoroute.profiles.domain.model.aggregates.Profile;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.ProfileId;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.UserReferenceId;
import com.trakto.traktoroute.profiles.domain.repositories.ProfileRepository;
import com.trakto.traktoroute.profiles.infrastructure.persistence.jpa.assemblers.ProfilePersistenceAssembler;
import com.trakto.traktoroute.profiles.infrastructure.persistence.jpa.entities.ProfilePersistenceEntity;
import com.trakto.traktoroute.profiles.infrastructure.persistence.jpa.repositories.ProfilePersistenceRepository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public class ProfileRepositoryImpl implements ProfileRepository {

    private final ProfilePersistenceRepository profilePersistenceRepository;

    public ProfileRepositoryImpl(
            ProfilePersistenceRepository profilePersistenceRepository
    ) {
        this.profilePersistenceRepository = profilePersistenceRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Profile> findById(ProfileId profileId) {
        return profilePersistenceRepository
                .findByProfileId(profileId)
                .map(ProfilePersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Profile> findByUserReferenceId(
            UserReferenceId userReferenceId
    ) {
        return profilePersistenceRepository
                .findByUserReferenceId(userReferenceId)
                .map(ProfilePersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Profile> findAll() {
        return profilePersistenceRepository
                .findAll()
                .stream()
                .map(ProfilePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    @Transactional
    public Profile save(Profile profile) {
        ProfilePersistenceEntity entity = profilePersistenceRepository
                .findByProfileId(profile.getProfileId())
                .orElse(null);

        if (entity == null) {
            entity = ProfilePersistenceAssembler.toPersistenceFromDomain(
                    profile
            );
        } else {
            ProfilePersistenceAssembler.updatePersistenceFromDomain(
                    profile,
                    entity
            );
        }

        ProfilePersistenceEntity savedEntity =
                profilePersistenceRepository.save(entity);

        return ProfilePersistenceAssembler.toDomainFromPersistence(
                savedEntity
        );
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(ProfileId profileId) {
        return profilePersistenceRepository.existsByProfileId(profileId);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByUserReferenceId(
            UserReferenceId userReferenceId
    ) {
        return profilePersistenceRepository.existsByUserReferenceId(
                userReferenceId
        );
    }
}