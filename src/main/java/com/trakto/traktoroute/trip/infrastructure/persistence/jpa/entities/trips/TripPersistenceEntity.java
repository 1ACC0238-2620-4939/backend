package com.trakto.traktoroute.trip.infrastructure.persistence.jpa.entities.trips;

import com.trakto.traktoroute.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import com.trakto.traktoroute.trip.domain.model.enums.TripStatus;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.DriverId;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.VehicleId;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.converters.trips.DriverIdPersistenceConverter;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.converters.trips.TripIdPersistenceConverter;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.converters.trips.VehicleIdPersistenceConverter;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.embeddables.trips.TripLocationPersistenceEmbeddable;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.embeddables.trips.TripRoutePlanPersistenceEmbeddable;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.embeddables.trips.TripSchedulePersistenceEmbeddable;
import com.trakto.traktoroute.trip.infrastructure.persistence.jpa.entities.stops.TripStopPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "trips")
@Getter
@Setter
@NoArgsConstructor
public class TripPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Convert(converter = TripIdPersistenceConverter.class)
    @Column(name = "trip_id",
            nullable = false,
            unique = true,
            updatable = false)
    private TripId tripId;

    @Convert(converter = DriverIdPersistenceConverter.class)
    @Column(name = "driver_id",
            nullable = false)
    private DriverId driverId;

    @Convert(converter = VehicleIdPersistenceConverter.class)
    @Column(name = "vehicle_id",
            nullable = false)
    private VehicleId vehicleId;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address",
                        column = @Column(name = "origin_address", nullable = false)),
            @AttributeOverride(name = "latitude",
                        column = @Column(name = "origin_latitude", nullable = false)),
            @AttributeOverride(name = "longitude",
                        column = @Column(name = "origin_longitude", nullable = false))
    })
    private TripLocationPersistenceEmbeddable origin;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "address",
                        column = @Column(name = "destination_address", nullable = false)),
            @AttributeOverride(name = "latitude",
                        column = @Column(name = "destination_latitude", nullable = false)),
            @AttributeOverride(name = "longitude",
                        column = @Column(name = "destination_longitude", nullable = false))
    })
    private TripLocationPersistenceEmbeddable destination;

    @Enumerated(EnumType.STRING)
    @Column(name = "status",
            nullable = false)
    private TripStatus status;

    @Embedded
    private TripSchedulePersistenceEmbeddable schedule;

    @Embedded
    private TripRoutePlanPersistenceEmbeddable routePlan;

    @OneToMany(mappedBy = "trip",
            cascade = {CascadeType.PERSIST, // Insertar nueva parada
                        CascadeType.MERGE}) // Actualizar parada existente
    private List<TripStopPersistenceEntity> stops = new ArrayList<>();

    public void addStop(TripStopPersistenceEntity stop) {
        stops.add(stop);
        stop.setTrip(this);
    }

}