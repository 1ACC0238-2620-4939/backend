package com.trakto.traktoroute.fleet.domain.model.aggregates;

import com.trakto.traktoroute.fleet.domain.model.events.vehicle.VehicleActivatedEvent;
import com.trakto.traktoroute.fleet.domain.model.events.vehicle.VehicleCreatedEvent;
import com.trakto.traktoroute.fleet.domain.model.events.vehicle.VehicleDeactivatedEvent;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.PlateNumber;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleCapacity;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleId;
import com.trakto.traktoroute.fleet.domain.model.enums.VehicleStatus;
import com.trakto.traktoroute.shared.domain.models.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Getter
public class Vehicle extends AbstractDomainAggregateRoot<Vehicle> {

    private VehicleId id;
    private PlateNumber plateNumber;
    private VehicleCapacity capacity;
    private VehicleStatus status;

    private Vehicle(
            VehicleId id,
            PlateNumber plateNumber,
            VehicleCapacity capacity,
            VehicleStatus status
    ) {
        this.id = Objects.requireNonNull(id,"Vehicle id cannot be null");
        this.plateNumber = Objects.requireNonNull(plateNumber,"Plate number cannot be null");
        this.capacity = Objects.requireNonNull(capacity,"Vehicle capacity cannot be null");
        this.status = Objects.requireNonNull(status,"Vehicle status cannot be null");
    }

    public static Vehicle create(PlateNumber plateNumber,
                                 VehicleCapacity capacity) {
        var vehicle = new Vehicle(
                VehicleId.generate(),
                plateNumber,
                capacity,
                VehicleStatus.ACTIVE
        );
        vehicle.registerDomainEvent(
                new VehicleCreatedEvent(
                        vehicle.id,
                        Instant.now())
        );

        return vehicle;
    }

    public static Vehicle reconstruct(
            VehicleId id,
            PlateNumber plateNumber,
            VehicleCapacity capacity,
            VehicleStatus status
    ) {
        return new Vehicle(
                id,
                plateNumber,
                capacity,
                status
        );
    }

    public void activate() {
        if (status == VehicleStatus.ACTIVE) {
            throw new IllegalStateException("Vehicle is already active");
        }

        status = VehicleStatus.ACTIVE;

        registerDomainEvent(
                new VehicleActivatedEvent(
                    id,
                    Instant.now()));
    }

    public void deactivate() {
        if (status == VehicleStatus.INACTIVE) {
            throw new IllegalStateException("Vehicle is already inactive");
        }

        status = VehicleStatus.INACTIVE;

        registerDomainEvent(
                new VehicleDeactivatedEvent(
                    id,
                    Instant.now()));
    }

    public void changePlateNumber(PlateNumber plateNumber) {
        this.plateNumber = Objects.requireNonNull(plateNumber, "Plate number cannot be null");
    }

    public boolean isActive() {
        return status == VehicleStatus.ACTIVE;
    }

    public void changeCapacity(VehicleCapacity capacity) {
        this.capacity = Objects.requireNonNull(
                capacity,
                "Vehicle capacity cannot be null"
        );
    }

}