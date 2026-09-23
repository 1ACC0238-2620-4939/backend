package com.trakto.traktoroute.trip.domain.repositories;

import com.trakto.traktoroute.trip.domain.model.aggregates.Trip;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;

import java.util.Optional;


public interface TripRepository {

    Optional<Trip> findById(TripId id);
    Trip save(Trip trip);
}
