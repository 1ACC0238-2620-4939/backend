package com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.assemblers;

import com.trakto.traktoroute.tracking.domain.model.aggregates.Tracking;
import com.trakto.traktoroute.tracking.domain.model.entities.TrackingStop;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.GeoLocation;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.PositionReport;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.StopId;
import com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.embeddables.GeoLocationPersistenceEmbeddable;
import com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.embeddables.PositionReportPersistenceEmbeddable;
import com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.entities.TrackingPersistenceEntity;
import com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.entities.TrackingStopPersistenceEntity;

import java.util.HashMap;
import java.util.Map;

public final class TrackingPersistenceAssembler {

    private TrackingPersistenceAssembler() {
    }

    public static Tracking toDomainFromPersistence(
            TrackingPersistenceEntity entity
    ) {
        PositionReport lastPositionReport = null;

        if (entity.getLastPositionReport() != null) {
            var report = entity.getLastPositionReport();

            lastPositionReport = new PositionReport(
                    new GeoLocation(
                            report.getLocation().getLatitude(),
                            report.getLocation().getLongitude()
                    ),
                    report.getRecordedAt()
            );
        }

        GeoLocation stationaryLocation = null;

        if (entity.getStationaryLocation() != null) {
            var location = entity.getStationaryLocation();

            stationaryLocation = new GeoLocation(
                    location.getLatitude(),
                    location.getLongitude()
            );
        }

        var stops = entity.getStops()
                .stream()
                .map(TrackingStopPersistenceAssembler::toDomainFromPersistence)
                .toList();

        return Tracking.reconstitute(
                entity.getTrackingId(),
                entity.getTripReferenceId(),
                entity.getStatus(),
                lastPositionReport,
                stationaryLocation,
                entity.getStationarySince(),
                stops
        );
    }

    public static TrackingPersistenceEntity toPersistenceFromDomain(
            Tracking tracking
    ) {
        var entity = new TrackingPersistenceEntity(
                tracking.getTrackingId(),
                tracking.getTripReferenceId(),
                tracking.getStatus()
        );

        updatePersistenceFromDomain(tracking, entity);

        return entity;
    }

    public static void updatePersistenceFromDomain(
            Tracking tracking,
            TrackingPersistenceEntity entity
    ) {
        entity.setStatus(tracking.getStatus());

        PositionReport lastPositionReport = tracking.getLastPositionReport();

        entity.setLastPositionReport(
                lastPositionReport == null
                        ? null
                        : new PositionReportPersistenceEmbeddable(
                        new GeoLocationPersistenceEmbeddable(
                                lastPositionReport.location().latitude(),
                                lastPositionReport.location().longitude()
                        ),
                        lastPositionReport.recordedAt()
                )
        );

        GeoLocation stationaryLocation = tracking.getStationaryLocation();

        entity.setStationaryLocation(
                stationaryLocation == null
                        ? null
                        : new GeoLocationPersistenceEmbeddable(
                        stationaryLocation.latitude(),
                        stationaryLocation.longitude()
                )
        );

        entity.setStationarySince(tracking.getStationarySince());

        Map<StopId, TrackingStopPersistenceEntity> existingStops =
                new HashMap<>();

        for (TrackingStopPersistenceEntity stopEntity : entity.getStops()) {
            existingStops.put(stopEntity.getStopId(), stopEntity);
        }

        for (TrackingStop stop : tracking.getStops()) {
            TrackingStopPersistenceEntity existingStop =
                    existingStops.get(stop.getStopId());

            if (existingStop == null) {
                TrackingStopPersistenceEntity newStop =
                        TrackingStopPersistenceAssembler
                                .toPersistenceFromDomain(stop);

                entity.addStop(newStop);
                existingStops.put(stop.getStopId(), newStop);
            } else {
                TrackingStopPersistenceAssembler
                        .updatePersistenceFromDomain(stop, existingStop);
            }
        }
    }
}