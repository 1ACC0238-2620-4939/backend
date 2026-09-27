package com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.assemblers.vehicle;

import com.trakto.traktoroute.fleet.domain.model.aggregates.Vehicle;
import com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.entities.vehicle.VehiclePersistenceEntity;

import java.util.Objects;

public class VehiclePersistenceAssembler {

    private VehiclePersistenceAssembler() {}

    public static Vehicle toDomainFromPersistence(VehiclePersistenceEntity entity) {
        if (entity == null) {
            return null;
        }

        return Vehicle.reconstruct(
                entity.getVehicleId(),
                entity.getPlateNumber(),
                entity.getCapacity(),
                entity.getStatus()
        );
    }

    public static VehiclePersistenceEntity toPersistenceFromDomain(Vehicle vehicle) {
        if (vehicle == null) {
            return null;
        }

        return new VehiclePersistenceEntity(
                vehicle.getId(),
                vehicle.getPlateNumber(),
                vehicle.getCapacity(),
                vehicle.getStatus()
        );
    }

    // Update Persistence
    public static void updatePersistenceFromDomain(Vehicle vehicle,
                                                   VehiclePersistenceEntity entity) {
        Objects.requireNonNull(vehicle);
        Objects.requireNonNull(entity);

        entity.setPlateNumber(vehicle.getPlateNumber());
        entity.setCapacity(vehicle.getCapacity());
        entity.setStatus(vehicle.getStatus());
    }
}