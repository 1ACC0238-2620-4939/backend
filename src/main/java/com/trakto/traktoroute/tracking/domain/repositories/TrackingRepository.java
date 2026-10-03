package com.trakto.traktoroute.tracking.domain.repositories;

import com.trakto.traktoroute.tracking.domain.model.aggregates.Tracking;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TrackingId;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TripReferenceId;

import java.util.Optional;

public interface TrackingRepository {

    Tracking save(Tracking tripTracking);

    Optional<Tracking> findById(TrackingId trackingId);

    Optional<Tracking> findByTripReferenceId(TripReferenceId tripReferenceId);

    boolean existsByTripReferenceId(TripReferenceId tripReferenceId);

}