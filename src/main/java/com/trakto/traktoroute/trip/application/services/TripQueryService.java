package com.trakto.traktoroute.trip.application.services;

import com.trakto.traktoroute.trip.application.queries.trips.*;
import com.trakto.traktoroute.trip.application.queries.stops.*;
import com.trakto.traktoroute.trip.domain.model.aggregates.Trip;
import com.trakto.traktoroute.trip.domain.model.entities.TripStop;
import com.trakto.traktoroute.trip.domain.repositories.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class TripQueryService {

    private final TripRepository tripRepository;

    public TripQueryService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Trip handle(GetTripByIdQuery query) {
        return tripRepository.findById(query.tripId())
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));
    }

    public List<Trip> handle(GetAllTripsQuery query) {
        return tripRepository.findAll();
    }

    public List<Trip> handle(GetTripsByStatusQuery query) {
        return tripRepository.findByStatus(query.status());
    }

    public List<TripStop> handle(GetStopsByTripIdQuery query) {

        Trip trip = tripRepository.findById(query.tripId())
                .orElseThrow(() -> new IllegalArgumentException("Trip not found"));

        return trip.getStops();
    }

    public TripStop handle(GetStopByTripIdAndStopIdQuery query) {

        Trip trip = tripRepository.findById(query.tripId())
                        .orElseThrow(() -> new IllegalArgumentException("Trip not found"));

        return trip.getStops().stream()
                .filter(stop -> stop.getId().equals(query.stopId()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Stop not found in trip"));
    }
}