package com.trakto.traktoroute.trip.domain.repositories;

import com.trakto.traktoroute.trip.domain.model.aggregates.Trip;
import com.trakto.traktoroute.trip.domain.model.enums.TripStatus;
import com.trakto.traktoroute.trip.domain.model.valueobjects.TripId;

import java.util.List;
import java.util.Optional;

public interface TripRepository {

    Optional<Trip> findById(TripId tripId);
    List<Trip> findAll();
    List<Trip> findByStatus(TripStatus status);
    Trip save(Trip trip);
    boolean existsById(TripId tripId);
}