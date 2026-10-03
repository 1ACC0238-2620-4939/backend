package com.trakto.traktoroute.trip.infrastructure.persistence.jpa.assemblers;

import com.trakto.traktoroute.trip.domain.model.aggregates.Trip;
import com.trakto.traktoroute.trip.domain.model.valueobjects.TripLocation;
import com.trakto.traktoroute.trip.domain.model.valueobjects.TripRoutePlan;
import com.trakto.traktoroute.trip.domain.model.valueobjects.TripSchedule;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.embeddables.TripLocationPersistenceEmbeddable;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.embeddables.TripRoutePlanPersistenceEmbeddable;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.embeddables.TripSchedulePersistenceEmbeddable;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.entities.TripPersistenceEntity;

import java.util.Objects;

public final class TripPersistenceAssembler {

    private TripPersistenceAssembler() {
    }

    // To Domain

    private static TripLocation toDomainLocation(
            TripLocationPersistenceEmbeddable location
    ) {
        return new TripLocation(
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude()
        );
    }

    private static TripSchedule toDomainSchedule(
            TripSchedulePersistenceEmbeddable schedule
    ) {
        return new TripSchedule(
                schedule.getScheduledAt(),
                schedule.getStartedAt(),
                schedule.getCompletedAt(),
                schedule.getCancelledAt()
        );
    }

    private static TripRoutePlan toDomainRoutePlan(
            TripRoutePlanPersistenceEmbeddable routePlan
    ) {
        return new TripRoutePlan(
                routePlan.getDistanceKm(),
                routePlan.getDurationMinutes(),
                routePlan.getRouteReference(),
                routePlan.getCalculatedAt()
        );
    }

    public static Trip toDomainFromPersistence(
            TripPersistenceEntity entity
    ) {
        Objects.requireNonNull(entity, "Trip entity cannot be null");

        return Trip.reconstitute(
                entity.getTripId(),
                entity.getDriverId(),
                entity.getVehicleId(),
                toDomainLocation(entity.getOrigin()),
                toDomainLocation(entity.getDestination()),
                entity.getStatus(),
                toDomainSchedule(entity.getSchedule()),
                toDomainRoutePlan(entity.getRoutePlan())
        );
    }

    // To Persistence

    private static TripLocationPersistenceEmbeddable toPersistenceLocation(
            TripLocation location
    ) {
        return new TripLocationPersistenceEmbeddable(
                location.address(),
                location.latitude(),
                location.longitude()
        );
    }

    private static TripSchedulePersistenceEmbeddable toPersistenceSchedule(
            TripSchedule schedule
    ) {
        return new TripSchedulePersistenceEmbeddable(
                schedule.scheduledAt(),
                schedule.startedAt(),
                schedule.completedAt(),
                schedule.cancelledAt()
        );
    }

    private static TripRoutePlanPersistenceEmbeddable toPersistenceRoutePlan(
            TripRoutePlan routePlan
    ) {
        return new TripRoutePlanPersistenceEmbeddable(
                routePlan.distanceKm(),
                routePlan.durationMinutes(),
                routePlan.routeReference(),
                routePlan.calculatedAt()
        );
    }

    public static TripPersistenceEntity toPersistenceFromDomain(Trip trip) {
        Objects.requireNonNull(trip, "Trip cannot be null");

        var entity = new TripPersistenceEntity();

        entity.setTripId(trip.getId());
        updatePersistenceFromDomain(trip, entity);

        return entity;
    }

    // Update Persistence

    public static void updatePersistenceFromDomain(
            Trip trip,
            TripPersistenceEntity entity
    ) {
        Objects.requireNonNull(trip, "Trip cannot be null");
        Objects.requireNonNull(entity, "Trip entity cannot be null");

        entity.setDriverId(trip.getDriverId());
        entity.setVehicleId(trip.getVehicleId());
        entity.setOrigin(toPersistenceLocation(trip.getOrigin()));
        entity.setDestination(toPersistenceLocation(trip.getDestination()));
        entity.setStatus(trip.getStatus());
        entity.setSchedule(toPersistenceSchedule(trip.getSchedule()));
        entity.setRoutePlan(toPersistenceRoutePlan(trip.getRoutePlan()));
    }
}