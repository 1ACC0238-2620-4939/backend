package com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.entities;

import com.trakto.traktoroute.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.StopId;
import com.trakto.traktoroute.tracking.domain.model.enums.StopReason;
import com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.converters.StopIdConverter;
import com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.converters.StopReasonConverter;
import com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.embeddables.GeoLocationPersistenceEmbeddable;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "tracking_stops")
@Setter
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class TrackingStopPersistenceEntity
        extends AuditableAbstractPersistenceEntity {

    @Convert(converter = StopIdConverter.class)
    @Column(name = "stop_id", nullable = false, updatable = false,unique = true)
    private StopId stopId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "latitude",
                    column = @Column(
                            name = "stop_latitude",
                            nullable = false
                    )
            ),
            @AttributeOverride(
                    name = "longitude",
                    column = @Column(
                            name = "stop_longitude",
                            nullable = false
                    )
            )
    })
    private GeoLocationPersistenceEmbeddable location;

    @Column(name = "started_at", nullable = false)
    private Instant startedAt;


    @Column(name = "ended_at")
    private Instant endedAt;

    @Convert(converter = StopReasonConverter.class)
    @Column(name = "reason", nullable = false, length = 20)
    private StopReason reason;

    @ManyToOne(fetch = FetchType.LAZY,
                optional = false)
    @JoinColumn(name = "trip_tracking_id",
            nullable = false,
            updatable = false)
    private TrackingPersistenceEntity tracking;

    public TrackingStopPersistenceEntity(
            StopId stopId,
            GeoLocationPersistenceEmbeddable location,
            Instant startedAt,
            Instant endedAt,
            StopReason reason
    ) {
        this.stopId = stopId;
        this.location = location;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.reason = reason;
    }
}