package com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.entities.vehicle;

import com.trakto.traktoroute.fleet.domain.model.enums.VehicleStatus;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.PlateNumber;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleCapacity;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleId;
import com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.converters.vehicle.PlateNumberConverter;
import com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.converters.vehicle.VehicleCapacityConverter;
import com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.converters.vehicle.VehicleIdConverter;
import com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.converters.vehicle.VehicleStatusConverter;
import com.trakto.traktoroute.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "vehicles")
@NoArgsConstructor
public class VehiclePersistenceEntity
        extends AuditableAbstractPersistenceEntity {

    @Convert(converter = VehicleIdConverter.class)
    @Column(name = "vehicle_id",
            nullable = false,
            updatable = false,
            unique = true)
    private VehicleId vehicleId;

    @Setter
    @Convert(converter = PlateNumberConverter.class)
    @Column(name = "plate_number",
            nullable = false,
            length = 7,
            unique = true)
    private PlateNumber plateNumber;

    @Setter
    @Convert(converter = VehicleCapacityConverter.class)
    @Column(name = "capacity",
            nullable = false,
            precision = 10,
            scale = 2)
    private VehicleCapacity capacity;

    @Setter
    @Convert(converter = VehicleStatusConverter.class)
    @Column(name = "status",
            nullable = false,
            length = 20)
    private VehicleStatus status;

    public VehiclePersistenceEntity(
            VehicleId vehicleId,
            PlateNumber plateNumber,
            VehicleCapacity capacity,
            VehicleStatus status) {
        this.vehicleId = Objects.requireNonNull(vehicleId, "Vehicle id cannot be null");
        this.plateNumber = Objects.requireNonNull(plateNumber, "Plate number cannot be null");
        this.capacity = Objects.requireNonNull(capacity, "Vehicle capacity cannot be null");
        this.status = Objects.requireNonNull(status, "Vehicle status cannot be null");
    }
}