package com.trakto.traktoroute.trip.application.services;

import com.trakto.traktoroute.shared.application.result.ApplicationError;
import com.trakto.traktoroute.shared.application.result.Result;
import com.trakto.traktoroute.trip.application.commands.trips.*;
import com.trakto.traktoroute.trip.application.commands.stops.*;
import com.trakto.traktoroute.trip.domain.model.aggregates.Trip;
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
    public Result<Trip, ApplicationError> handle(CreateTripCommand command) {
        try {
            Trip trip = Trip.create(command.driverId(),
                                    command.vehicleId(),
                                    command.origin(),
                                    command.destination(),
                                    command.schedule(),
                                    command.routePlan());

            Trip savedTrip = tripRepository.save(trip);
            return Result.success(savedTrip);

        } catch (Exception e) {
            return Result.failure(ApplicationError.validationError(
                            "Trip",
                            e.getMessage()));
        }
    }

    @Transactional
    public Result<Trip, ApplicationError> handle(StartTripCommand command) {

        var tripOptional = tripRepository.findById(command.tripId());

        if (tripOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound(
                            "Trip",
                            "Trip not found"));
        }
        Trip trip = tripOptional.get();

        try {
            trip.start(command.startedAt());
            Trip savedTrip = tripRepository.save(trip);
            return Result.success(savedTrip);

        } catch (Exception e) {
            return Result.failure(ApplicationError.validationError(
                            "Trip",
                            e.getMessage()));
        }
    }

    @Transactional
    public Result<Trip, ApplicationError> handle(CompleteTripCommand command) {

        var tripOptional = tripRepository.findById(command.tripId());
        if (tripOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound(
                            "Trip",
                            "Trip not found"));
        }

        Trip trip = tripOptional.get();

        try {
            trip.complete(command.completedAt());
            Trip savedTrip = tripRepository.save(trip);
            return Result.success(savedTrip);

        } catch (Exception e) {
            return Result.failure(ApplicationError.validationError(
                            "Trip",
                            e.getMessage()));
        }
    }

    @Transactional
    public Result<Trip, ApplicationError> handle(CancelTripCommand command) {

        var tripOptional = tripRepository.findById(command.tripId());

        if (tripOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound(
                            "Trip",
                            "Trip not found"));
        }

        Trip trip = tripOptional.get();

        try {
            trip.cancel(command.cancelledAt());
            Trip savedTrip = tripRepository.save(trip);
            return Result.success(savedTrip);

        } catch (Exception e) {
            return Result.failure(ApplicationError.notFound(
                            "Trip",
                            e.getMessage()));
        }
    }


    @Transactional
    public Result<Trip, ApplicationError> handle(StartTripStopCommand command) {

        var tripOptional = tripRepository.findById(command.tripId());

        if (tripOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound(
                            "Trip",
                            "Trip not found"));
        }

        Trip trip = tripOptional.get();

        try {
            trip.registerStop(
                    command.location(),
                    command.startedAt(),
                    command.reason()
            );

            Trip savedTrip = tripRepository.save(trip);

            return Result.success(savedTrip);

        } catch (Exception e) {
            return Result.failure(ApplicationError.notFound(
                            "TripStop",
                            e.getMessage()));
        }
    }


    @Transactional
    public Result<Trip, ApplicationError> handle(FinishTripStopCommand command) {

        var tripOptional = tripRepository.findById(command.tripId());

        if (tripOptional.isEmpty()) {
            return Result.failure(ApplicationError.notFound(
                            "Trip",
                            "Trip not found"));
        }
        Trip trip = tripOptional.get();

        try {
            trip.finishStop(command.stopId(),
                            command.endedAt()
            );

            Trip savedTrip = tripRepository.save(trip);
            return Result.success(savedTrip);

        } catch (Exception e) {
            return Result.failure(ApplicationError.notFound(
                            "TripStop",
                            e.getMessage()));
        }
    }
}