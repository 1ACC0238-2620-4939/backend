package com.trakto.traktoroute.trip.infrastructure.persistence.jpa.assemblers;

import com.trakto.traktoroute.trip.domain.model.aggregates.Trip;
import com.trakto.traktoroute.trip.domain.model.entities.TripStop;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopId;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripLocation;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripRoutePlan;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripSchedule;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.embeddables.trip.TripLocationPersistenceEmbeddable;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.embeddables.trip.TripRoutePlanPersistenceEmbeddable;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.embeddables.trip.TripSchedulePersistenceEmbeddable;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.entities.TripPersistenceEntity;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.entities.TripStopPersistenceEntity;

import java.util.HashMap;
import java.util.Objects;

public final class TripPersistenceAssembler {

    private TripPersistenceAssembler() {
    }

    // To Domain
    private static TripLocation toDomainLocation(TripLocationPersistenceEmbeddable location) {
        return new TripLocation(
                location.getAddress(),
                location.getLatitude(),
                location.getLongitude()
        );
    }

    private static TripSchedule toDomainSchedule(TripSchedulePersistenceEmbeddable schedule) {
        return new TripSchedule(
                schedule.getScheduledAt(),
                schedule.getStartedAt(),
                schedule.getCompletedAt()
        );
    }

    private static TripRoutePlan toDomainRoutePlan(TripRoutePlanPersistenceEmbeddable routePlan) {
        return new TripRoutePlan(
                routePlan.getDistanceKm(),
                routePlan.getDurationMinutes(),
                routePlan.getRouteReference(),
                routePlan.getCalculatedAt()
        );
    }

    public static Trip toDomainFromPersistence(TripPersistenceEntity entity) {
        Objects.requireNonNull(entity);

        var stops = entity.getStops()
                .stream()
                .map(TripStopPersistenceAssembler::toDomainFromPersistence)
                .toList();

        return Trip.reconstitute(
                entity.getTripId(),
                entity.getDriverId(),
                entity.getVehicleId(),
                toDomainLocation(entity.getOrigin()),
                toDomainLocation(entity.getDestination()),
                entity.getStatus(),
                toDomainSchedule(entity.getSchedule()),
                toDomainRoutePlan(entity.getRoutePlan()),
                stops
        );
    }

    // To Persistence
    private static TripLocationPersistenceEmbeddable toPersistenceLocation(TripLocation location) {
        return new TripLocationPersistenceEmbeddable(
                location.address(),
                location.latitude(),
                location.longitude()
        );
    }

    private static TripSchedulePersistenceEmbeddable toPersistenceSchedule(TripSchedule schedule) {
        return new TripSchedulePersistenceEmbeddable(
                schedule.scheduledAt(),
                schedule.startedAt(),
                schedule.completedAt()
        );
    }

    private static TripRoutePlanPersistenceEmbeddable toPersistenceRoutePlan(TripRoutePlan routePlan) {
        return new TripRoutePlanPersistenceEmbeddable(
                routePlan.distanceKm(),
                routePlan.durationMinutes(),
                routePlan.routeReference(),
                routePlan.calculatedAt()
        );
    }

    public static TripPersistenceEntity toPersistenceFromDomain(Trip trip) {
        Objects.requireNonNull(trip);

        var entity = new TripPersistenceEntity();

        entity.setTripId(trip.getId());
        entity.setDriverId(trip.getDriverId());
        entity.setVehicleId(trip.getVehicleId());
        entity.setOrigin(toPersistenceLocation(trip.getOrigin()));
        entity.setDestination(toPersistenceLocation(trip.getDestination()));
        entity.setStatus(trip.getStatus());
        entity.setSchedule(toPersistenceSchedule(trip.getSchedule()));
        entity.setRoutePlan(toPersistenceRoutePlan(trip.getRoutePlan()));

        return entity;
    }

    // Update Persistence

    public static void updatePersistenceFromDomain(Trip trip,
                                                    TripPersistenceEntity entity) {
        Objects.requireNonNull(trip);
        Objects.requireNonNull(entity);

        entity.setStatus(trip.getStatus());
        entity.setSchedule(toPersistenceSchedule(trip.getSchedule()));
        entity.setRoutePlan(toPersistenceRoutePlan(trip.getRoutePlan()));

        synchronizeStops(trip, entity);
    }

    // Synchronize Stops
    private static void synchronizeStops(Trip trip,
                                        TripPersistenceEntity entity) {

            var persistenceStopsById = new HashMap<StopId, TripStopPersistenceEntity>();

        for (TripStopPersistenceEntity persistenceStop : entity.getStops()) {
            persistenceStopsById.put(
                    persistenceStop.getStopId(),
                    persistenceStop
            );
        }

        for (TripStop domainStop : trip.getStops()) {
            TripStopPersistenceEntity persistenceStop =
                    persistenceStopsById.get(domainStop.getId());

            if (persistenceStop == null) {

                TripStopPersistenceEntity newPersistenceStop =
                        TripStopPersistenceAssembler.toPersistenceFromDomain(domainStop);

                entity.addStop(newPersistenceStop);

            }else {
                TripStopPersistenceAssembler
                        .updatePersistenceFromDomain(domainStop,
                                                    persistenceStop
                        );
            }
        }
    }
}