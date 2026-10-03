package com.trakto.traktoroute.trip.application.services;

import com.trakto.traktoroute.shared.application.result.ApplicationError;
import com.trakto.traktoroute.shared.application.result.Result;
import com.trakto.traktoroute.trip.application.commands.CancelTripCommand;
import com.trakto.traktoroute.trip.application.commands.CompleteTripCommand;
import com.trakto.traktoroute.trip.application.commands.CreateTripCommand;
import com.trakto.traktoroute.trip.application.commands.StartTripCommand;
import com.trakto.traktoroute.trip.domain.model.aggregates.Trip;
import com.trakto.traktoroute.trip.domain.model.valueobjects.TripId;
import com.trakto.traktoroute.trip.domain.repositories.TripRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TripCommandService {

    private final TripRepository tripRepository;

    public TripCommandService(TripRepository tripRepository) {
        this.tripRepository = tripRepository;
    }

    @Transactional
    public Result<TripId, ApplicationError> handle(
            CreateTripCommand command
    ) {
        Trip trip;

        try {
            trip = Trip.create(
                    command.driverId(),
                    command.vehicleId(),
                    command.origin(),
                    command.destination(),
                    command.schedule(),
                    command.routePlan()
            );
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Result.failure(
                    ApplicationError.validationError("Trip", e.getMessage())
            );
        }

        Trip savedTrip = tripRepository.save(trip);

        return Result.success(savedTrip.getId());
    }

    @Transactional
    public Result<Void, ApplicationError> handle(
            StartTripCommand command
    ) {
        var tripOptional = tripRepository.findById(command.tripId());

        if (tripOptional.isEmpty()) {
            return Result.failure(
                    ApplicationError.notFound("Trip", "Trip not found")
            );
        }

        Trip trip = tripOptional.get();

        try {
            trip.start(command.startedAt());
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Result.failure(
                    ApplicationError.validationError("Trip", e.getMessage())
            );
        }

        tripRepository.save(trip);

        return Result.success(null);
    }

    @Transactional
    public Result<Void, ApplicationError> handle(
            CompleteTripCommand command
    ) {
        var tripOptional = tripRepository.findById(command.tripId());

        if (tripOptional.isEmpty()) {
            return Result.failure(
                    ApplicationError.notFound("Trip", "Trip not found")
            );
        }

        Trip trip = tripOptional.get();

        try {
            trip.complete(command.completedAt());
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Result.failure(
                    ApplicationError.validationError("Trip", e.getMessage())
            );
        }

        tripRepository.save(trip);

        return Result.success(null);
    }

    @Transactional
    public Result<Void, ApplicationError> handle(
            CancelTripCommand command
    ) {
        var tripOptional = tripRepository.findById(command.tripId());

        if (tripOptional.isEmpty()) {
            return Result.failure(
                    ApplicationError.notFound("Trip", "Trip not found")
            );
        }

        Trip trip = tripOptional.get();

        try {
            trip.cancel(command.cancelledAt());
        } catch (IllegalArgumentException | IllegalStateException e) {
            return Result.failure(
                    ApplicationError.validationError("Trip", e.getMessage())
            );
        }

        tripRepository.save(trip);

        return Result.success(null);
    }
}