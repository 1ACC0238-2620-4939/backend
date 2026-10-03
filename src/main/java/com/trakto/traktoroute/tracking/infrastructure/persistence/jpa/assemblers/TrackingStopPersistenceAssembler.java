package com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.assemblers;

import com.trakto.traktoroute.tracking.domain.model.entities.TrackingStop;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.GeoLocation;
import com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.embeddables.GeoLocationPersistenceEmbeddable;
import com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.entities.TrackingStopPersistenceEntity;

public final class TrackingStopPersistenceAssembler {

    private TrackingStopPersistenceAssembler() {
    }

    public static TrackingStop toDomainFromPersistence(
            TrackingStopPersistenceEntity entity
    ) {
        return new TrackingStop(
                entity.getStopId(),
                new GeoLocation(
                        entity.getLocation().getLatitude(),
                        entity.getLocation().getLongitude()
                ),
                entity.getStartedAt(),
                entity.getEndedAt(),
                entity.getReason()
        );
    }

    public static TrackingStopPersistenceEntity toPersistenceFromDomain(
            TrackingStop stop
    ) {
        return new TrackingStopPersistenceEntity(
                stop.getStopId(),
                new GeoLocationPersistenceEmbeddable(
                        stop.getLocation().latitude(),
                        stop.getLocation().longitude()
                ),
                stop.getStartedAt(),
                stop.getEndedAt(),
                stop.getReason()
        );
    }

    public static void updatePersistenceFromDomain(
            TrackingStop stop,
            TrackingStopPersistenceEntity entity
    ) {
        entity.setLocation(
                new GeoLocationPersistenceEmbeddable(
                        stop.getLocation().latitude(),
                        stop.getLocation().longitude()
                )
        );

        entity.setStartedAt(stop.getStartedAt());
        entity.setEndedAt(stop.getEndedAt());
        entity.setReason(stop.getReason());
    }
}