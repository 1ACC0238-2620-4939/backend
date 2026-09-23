package com.trakto.traktoroute.trip.infrastructure.persistence.jpa.entities;

import com.trakto.traktoroute.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import com.trakto.traktoroute.trip.domain.model.enums.StopReason;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopId;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.TripInstant;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.converters.stop.StopIdPersistenceConverter;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.converters.stop.TripInstantPersistenceConverter;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.embeddables.stop.StopLocationPersistenceEmbeddable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
        name = "trip_stops",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "uk_trip_stops_stop_id",
                        columnNames = "stop_id"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
public class TripStopPersistenceEntity
        extends AuditableAbstractPersistenceEntity {

    @Convert(converter = StopIdPersistenceConverter.class)
    @Column(
            name = "stop_id",
            nullable = false,
            unique = true,
            updatable = false
    )
    private StopId stopId;

    @ManyToOne(
            fetch = FetchType.LAZY,
            optional = false
    )
    @JoinColumn(
            name = "trip_id",
            nullable = false
    )
    private TripPersistenceEntity trip;

    @Embedded
    private StopLocationPersistenceEmbeddable location;

    @Convert(converter = TripInstantPersistenceConverter.class)
    @Column(
            name = "started_at",
            nullable = false
    )
    private TripInstant startedAt;

    @Convert(converter = TripInstantPersistenceConverter.class)
    @Column(name = "ended_at")
    private TripInstant endedAt;

    @Enumerated(EnumType.STRING)
    @Column(
            name = "reason",
            nullable = false
    )
    private StopReason reason;
}