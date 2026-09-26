package com.trakto.traktoroute.trip.domain.model.aggregates;

import com.trakto.traktoroute.shared.domain.models.aggregates.AbstractDomainAggregateRoot;
import com.trakto.traktoroute.trip.domain.model.entities.TripStop;
import com.trakto.traktoroute.trip.domain.model.enums.StopReason;
import com.trakto.traktoroute.trip.domain.model.enums.TripStatus;
import com.trakto.traktoroute.trip.domain.model.events.*;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopId;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopLocation;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.TripInstant;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.*;
import lombok.AccessLevel;
import lombok.Getter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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

    @Getter(AccessLevel.NONE)
    private final List<TripStop> stops;

    private Trip(
            TripId id,
            DriverId driverId,
            VehicleId vehicleId,
            TripLocation origin,
            TripLocation destination,
            TripStatus status,
            TripSchedule schedule,
            TripRoutePlan routePlan,
            List<TripStop> stops
    ) {
        this.id = Objects.requireNonNull(id,"Trip id cannot be null");
        this.driverId = Objects.requireNonNull(driverId,"Driver id cannot be null");
        this.vehicleId = Objects.requireNonNull(vehicleId,"Vehicle id cannot be null");
        this.origin = Objects.requireNonNull(origin,"Origin cannot be null");
        this.destination = Objects.requireNonNull(destination,"Destination cannot be null");
        this.status = Objects.requireNonNull(status,"Trip status cannot be null");
        this.schedule = Objects.requireNonNull(schedule,"Trip schedule cannot be null");
        this.routePlan = Objects.requireNonNull(routePlan,"Trip route plan cannot be null");
        this.stops = new ArrayList<>(Objects.requireNonNull(stops)
        );
    }
    public List<TripStop> getStops() {
        return List.copyOf(stops);
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
                routePlan,
                List.of()
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
            TripRoutePlan routePlan,
            List<TripStop> stops
    ) {
        return new Trip(
                id,
                driverId,
                vehicleId,
                origin,
                destination,
                status,
                schedule,
                routePlan,
                stops
        );
    }

    public void start(Instant startedAt) {

        if (status != TripStatus.SCHEDULED) {
            throw new IllegalStateException("Only scheduled trips can be started");
        }

        this.schedule = schedule.start(startedAt);
        this.status = TripStatus.IN_PROGRESS;

        registerDomainEvent(new TripStartedEvent(id, startedAt));
    }

    public void complete(Instant completedAt) {

        if (status != TripStatus.IN_PROGRESS) {
            throw new IllegalStateException("Only trips in progress can be completed");
        }

        this.schedule = schedule.complete(completedAt);
        this.status = TripStatus.COMPLETED;

        registerDomainEvent(new TripCompletedEvent(id, completedAt));
    }

    public void cancel(Instant cancelledAt) {

        if (status == TripStatus.COMPLETED) {
            throw new IllegalStateException("Completed trip cannot be cancelled");
        }

        if (status == TripStatus.CANCELLED) {
            throw new IllegalStateException("Trip is already cancelled");
        }

        this.schedule = schedule.cancel(cancelledAt);
        this.status = TripStatus.CANCELLED;

        registerDomainEvent(
                new TripCancelledEvent(
                        id,
                        cancelledAt));
    }

    public void registerStop(
            StopLocation location,
            TripInstant startedAt,
            StopReason reason
    ) {

        if (status != TripStatus.IN_PROGRESS) {
            throw new IllegalStateException("Stops can only be registered during an active trip");
        }

        boolean hasOpenStop = stops.stream()
                .anyMatch(TripStop::isOpen);

        if (hasOpenStop) {
            throw new IllegalStateException("Trip already has an active stop");
        }

        var stop = TripStop.start(
                location,
                startedAt,
                reason
        );

        stops.add(stop);

        registerDomainEvent(
                new TripStopRegisteredEvent(id,
                                            stop.getId(),
                                            startedAt.value()));
    }

    public void finishStop(
            StopId stopId,
            TripInstant endedAt
    ) {
        var stop = stops.stream()
                .filter(data -> data.getId().equals(stopId))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Stop does not belong to this trip"));

        stop.finish(endedAt);

        registerDomainEvent(
                new TripStopFinishedEvent(id,
                                        stopId,
                                        endedAt.value()
                )
        );
    }

}
