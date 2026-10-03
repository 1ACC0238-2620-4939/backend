package com.trakto.traktoroute.profiles.domain.repositories;

import com.trakto.traktoroute.profiles.domain.model.aggregates.Profile;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.ProfileId;
import com.trakto.traktoroute.profiles.domain.model.valueobjects.UserReferenceId;

import java.util.List;
import java.util.Optional;

public interface ProfileRepository {

    Optional<Profile> findById(ProfileId profileId);

    Optional<Profile> findByUserReferenceId(UserReferenceId userReferenceId);

    List<Profile> findAll();

    boolean existsById(ProfileId profileId);

    boolean existsByUserReferenceId(UserReferenceId userReferenceId);

    Profile save(Profile profile);
}