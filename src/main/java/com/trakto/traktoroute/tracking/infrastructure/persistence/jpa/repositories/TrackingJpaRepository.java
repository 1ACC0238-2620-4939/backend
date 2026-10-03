package com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.repositories;

import com.trakto.traktoroute.tracking.domain.model.valueobjects.TrackingId;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TripReferenceId;
import com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.entities.TrackingPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrackingJpaRepository
        extends JpaRepository<TrackingPersistenceEntity, Long> {

    Optional<TrackingPersistenceEntity> findByTrackingId(
            TrackingId trackingId
    );

    Optional<TrackingPersistenceEntity> findByTripReferenceId(
            TripReferenceId tripReferenceId
    );

    boolean existsByTripReferenceId(
            TripReferenceId tripReferenceId
    );
}