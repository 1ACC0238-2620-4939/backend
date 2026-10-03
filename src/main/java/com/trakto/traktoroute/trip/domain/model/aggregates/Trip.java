package com.trakto.traktoroute.trip.domain.model.aggregates;

import com.trakto.traktoroute.shared.domain.models.aggregates.AbstractDomainAggregateRoot;
import com.trakto.traktoroute.trip.domain.model.enums.TripStatus;
import com.trakto.traktoroute.trip.domain.model.events.*;
import com.trakto.traktoroute.trip.domain.model.valueobjects.*;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Getter
public class Trip extends AbstractDomainAggregateRoot<Trip> {

    private final TripId id;

    private DriverId driverId;
    private VehicleId vehicleId;

    private TripLocation origin;
    private TripLocation destination;

    private TripStatus status;
    private TripSchedule schedule;
    private TripRoutePlan routePlan;

    private Trip(
            TripId id,
            DriverId driverId,
            VehicleId vehicleId,
            TripLocation origin,
            TripLocation destination,
            TripStatus status,
            TripSchedule schedule,
            TripRoutePlan routePlan
    ) {
        this.id = Objects.requireNonNull(
                id, "Trip id cannot be null"
        );
        this.driverId = Objects.requireNonNull(
                driverId, "Driver id cannot be null"
        );
        this.vehicleId = Objects.requireNonNull(
                vehicleId, "Vehicle id cannot be null"
        );
        this.origin = Objects.requireNonNull(
                origin, "Origin cannot be null"
        );
        this.destination = Objects.requireNonNull(
                destination, "Destination cannot be null"
        );
        this.status = Objects.requireNonNull(
                status, "Trip status cannot be null"
        );
        this.schedule = Objects.requireNonNull(
                schedule, "Trip schedule cannot be null"
        );
        this.routePlan = Objects.requireNonNull(
                routePlan, "Trip route plan cannot be null"
        );
    }

    public static Trip create(
            DriverId driverId,
            VehicleId vehicleId,
            TripLocation origin,
            TripLocation destination,
            TripSchedule schedule,
            TripRoutePlan routePlan
    ) {
        var trip = new Trip(
                TripId.generate(),
                driverId,
                vehicleId,
                origin,
                destination,
                TripStatus.SCHEDULED,
                schedule,
                routePlan
        );

        trip.registerDomainEvent(
                new TripCreatedEvent(
                        trip.id,
                        driverId,
                        vehicleId,
                        Instant.now()
                )
        );

        return trip;
    }

    public static Trip reconstitute(
            TripId id,
            DriverId driverId,
            VehicleId vehicleId,
            TripLocation origin,
            TripLocation destination,
            TripStatus status,
            TripSchedule schedule,
            TripRoutePlan routePlan
    ) {
        return new Trip(
                id,
                driverId,
                vehicleId,
                origin,
                destination,
                status,
                schedule,
                routePlan
        );
    }

    public void start(Instant startedAt) {
        Objects.requireNonNull(
                startedAt, "Start time cannot be null"
        );

        if (status != TripStatus.SCHEDULED) {
            throw new IllegalStateException(
                    "Only scheduled trips can be started"
            );
        }

        this.schedule = schedule.start(startedAt);
        this.status = TripStatus.IN_PROGRESS;

        registerDomainEvent(
                new TripStartedEvent(id, startedAt)
        );
    }

    public void complete(Instant completedAt) {
        Objects.requireNonNull(
                completedAt, "Completion time cannot be null"
        );

        if (status != TripStatus.IN_PROGRESS) {
            throw new IllegalStateException(
                    "Only trips in progress can be completed"
            );
        }

        this.schedule = schedule.complete(completedAt);
        this.status = TripStatus.COMPLETED;

        registerDomainEvent(
                new TripCompletedEvent(id, completedAt)
        );
    }

    public void cancel(Instant cancelledAt) {
        Objects.requireNonNull(
                cancelledAt, "Cancellation time cannot be null"
        );

        if (status == TripStatus.COMPLETED) {
            throw new IllegalStateException(
                    "Completed trip cannot be cancelled"
            );
        }

        if (status == TripStatus.CANCELLED) {
            throw new IllegalStateException(
                    "Trip is already cancelled"
            );
        }

        this.schedule = schedule.cancel(cancelledAt);
        this.status = TripStatus.CANCELLED;

        registerDomainEvent(
                new TripCancelledEvent(id, cancelledAt)
        );
    }
}