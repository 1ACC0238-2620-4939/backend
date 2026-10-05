package com.trakto.traktoroute.trip.application.services;

import com.trakto.traktoroute.trip.application.queries.GetAllTripsQuery;
import com.trakto.traktoroute.trip.application.queries.GetTripByIdQuery;
import com.trakto.traktoroute.trip.application.queries.GetTripsByStatusQuery;
import com.trakto.traktoroute.trip.domain.model.aggregates.Trip;
import com.trakto.traktoroute.trip.domain.repositories.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.trakto.traktoroute.shared.application.result.Result;
import com.trakto.traktoroute.shared.application.result.ApplicationError;

@Service
@Transactional(readOnly = true)
public class TripQueryService {

    private final TripRepository tripRepository;

    public TripQueryService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    public Result<Trip, ApplicationError> handle(GetTripByIdQuery query) {
        return tripRepository.findById(query.tripId())
                .<Result<Trip, ApplicationError>>map(Result::success)
                .orElseGet(() -> Result.failure(ApplicationError.notFound("Trip", "Trip not found")));
    }

    public List<Trip> handle(GetAllTripsQuery query) {
        return tripRepository.findAll();
    }

    public List<Trip> handle(GetTripsByStatusQuery query) {
        return tripRepository.findByStatus(query.status());
    }
}
