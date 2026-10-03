package com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.adapters;

import com.trakto.traktoroute.tracking.domain.model.aggregates.Tracking;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TrackingId;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TripReferenceId;
import com.trakto.traktoroute.tracking.domain.repositories.TrackingRepository;
import com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.assemblers.TrackingPersistenceAssembler;
import com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.entities.TrackingPersistenceEntity;
import com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.repositories.TrackingJpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class TrackingRepositoryImpl implements TrackingRepository {

    private final TrackingJpaRepository trackingJpaRepository;

    public TrackingRepositoryImpl(
            TrackingJpaRepository trackingJpaRepository
    ) {
        this.trackingJpaRepository = trackingJpaRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tracking> findById(TrackingId trackingId) {
        return trackingJpaRepository
                .findByTrackingId(trackingId)
                .map(TrackingPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Tracking> findByTripReferenceId(
            TripReferenceId tripReferenceId
    ) {
        return trackingJpaRepository
                .findByTripReferenceId(tripReferenceId)
                .map(TrackingPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    @Transactional
    public Tracking save(Tracking tracking) {
        TrackingPersistenceEntity entity = trackingJpaRepository
                .findByTrackingId(tracking.getTrackingId())
                .orElse(null);

        if (entity == null) {
            entity = TrackingPersistenceAssembler
                    .toPersistenceFromDomain(tracking);
        } else {
            TrackingPersistenceAssembler
                    .updatePersistenceFromDomain(tracking, entity);
        }

        trackingJpaRepository.save(entity);

        return tracking;
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByTripReferenceId(
            TripReferenceId tripReferenceId
    ) {
        return trackingJpaRepository
                .existsByTripReferenceId(tripReferenceId);
    }
}