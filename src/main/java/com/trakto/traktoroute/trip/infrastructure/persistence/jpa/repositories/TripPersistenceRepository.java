package com.trakto.traktoroute.trip.infrastructure.persistence.jpa.repositories;

import com.trakto.traktoroute.trip.domain.model.enums.TripStatus;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.entities.TripPersistenceEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TripPersistenceRepository
        extends JpaRepository<TripPersistenceEntity, Long> {

    @EntityGraph(attributePaths = "stops")
    Optional<TripPersistenceEntity> findByTripId(UUID tripId);

    @EntityGraph(attributePaths = "stops")
    List<TripPersistenceEntity> findByStatus(TripStatus status);

    boolean existsByTripId(UUID tripId);

    @Override
    @EntityGraph(attributePaths = "stops")
    List<TripPersistenceEntity> findAll();
}