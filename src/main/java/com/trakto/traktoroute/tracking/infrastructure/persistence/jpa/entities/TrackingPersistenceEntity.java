package com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.entities;

import com.trakto.traktoroute.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TrackingId;
import com.trakto.traktoroute.tracking.domain.model.enums.TrackingStatus;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TripReferenceId;
import com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.converters.TrackingIdConverter;
import com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.converters.TripReferenceIdConverter;
import com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.embeddables.GeoLocationPersistenceEmbeddable;
import com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.embeddables.PositionReportPersistenceEmbeddable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trip_trackings")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrackingPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Convert(converter = TrackingIdConverter.class)
    @Column(name = "tracking_id", nullable = false, updatable = false,unique = true)
    private TrackingId trackingId;

    @Convert(converter = TripReferenceIdConverter.class)
    @Column(name = "trip_reference_id", nullable = false, updatable = false,unique = true)
    private TripReferenceId tripReferenceId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private TrackingStatus status;

    @Embedded
    private PositionReportPersistenceEmbeddable lastPositionReport;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "latitude",
                    column = @Column(name = "stationary_latitude")
            ),
            @AttributeOverride(
                    name = "longitude",
                    column = @Column(name = "stationary_longitude")
            )
    })
    private GeoLocationPersistenceEmbeddable stationaryLocation;

    @Column(name = "stationary_since")
    private Instant stationarySince;

    @OneToMany(mappedBy = "tracking",
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            fetch = FetchType.LAZY)
    @OrderBy("startedAt ASC")
    private List<TrackingStopPersistenceEntity> stops = new ArrayList<>();

    public TrackingPersistenceEntity(
            TrackingId trackingId,
            TripReferenceId tripReferenceId,
            TrackingStatus status
    ) {
        this.trackingId = trackingId;
        this.tripReferenceId = tripReferenceId;
        this.status = status;
    }

    public void addStop(TrackingStopPersistenceEntity stop) {
        stops.add(stop);
        stop.setTracking(this);
    }

    public void removeStop(TrackingStopPersistenceEntity stop) {
        if (stops.remove(stop)) {
            stop.setTracking(null);
        }
    }
}