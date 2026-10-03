package com.trakto.traktoroute.trip.infrastructure.persistence.jpa.repositories;

import com.trakto.traktoroute.trip.domain.model.enums.TripStatus;
import com.trakto.traktoroute.trip.domain.model.valueobjects.TripId;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.entities.TripPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TripPersistenceRepository
        extends JpaRepository<TripPersistenceEntity, Long> {

    Optional<TripPersistenceEntity> findByTripId(TripId tripId);

    List<TripPersistenceEntity> findByStatus(TripStatus status);

    boolean existsByTripId(TripId tripId);
}