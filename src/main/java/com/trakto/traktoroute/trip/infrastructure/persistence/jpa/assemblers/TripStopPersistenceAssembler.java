package com.trakto.traktoroute.trip.infrastructure.persistence.jpa.assemblers;

import com.trakto.traktoroute.trip.domain.model.entities.TripStop;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopLocation;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.embeddables.stop.StopLocationPersistenceEmbeddable;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.entities.TripStopPersistenceEntity;

import java.util.Objects;

public final class TripStopPersistenceAssembler {

    private TripStopPersistenceAssembler() {}


    // To Domain
    private static StopLocation toDomainLocation(StopLocationPersistenceEmbeddable location) {
        return new StopLocation(location.getLatitude(),
                location.getLongitude());
    }

    public static TripStop toDomainFromPersistence(TripStopPersistenceEntity entity) {
        Objects.requireNonNull(entity);

        return TripStop.reconstitute(
                entity.getStopId(),
                toDomainLocation(entity.getLocation()),
                entity.getStartedAt(),
                entity.getEndedAt(),
                entity.getReason()
        );
    }

    // To Persistence
    private static StopLocationPersistenceEmbeddable toPersistenceLocation(StopLocation location) {
        return new StopLocationPersistenceEmbeddable(
                location.latitude(),
                location.longitude()
        );
    }

    public static TripStopPersistenceEntity toPersistenceFromDomain(TripStop stop) {
        Objects.requireNonNull(stop);

        var entity = new TripStopPersistenceEntity();

        entity.setStopId(stop.getId());
        entity.setLocation(toPersistenceLocation(stop.getLocation()));
        entity.setStartedAt(stop.getStartedAt());
        entity.setEndedAt(stop.getEndedAt());
        entity.setReason(stop.getReason());

        return entity;
    }

    //Update Persistence
    public static void updatePersistenceFromDomain(TripStop stop,
                                    TripStopPersistenceEntity entity) {
        Objects.requireNonNull(stop);
        Objects.requireNonNull(entity);

        entity.setLocation(toPersistenceLocation(stop.getLocation()));
        entity.setStartedAt(stop.getStartedAt());
        entity.setEndedAt(stop.getEndedAt());
        entity.setReason(stop.getReason());
    }




}