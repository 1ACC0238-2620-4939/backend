package com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.repositories.vehicle;

import com.trakto.traktoroute.fleet.domain.model.enums.VehicleStatus;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.PlateNumber;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleId;
import com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.entities.vehicle.VehiclePersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VehiclePersistenceRepository
        extends JpaRepository<VehiclePersistenceEntity, Long> {

    Optional<VehiclePersistenceEntity> findByVehicleId(VehicleId vehicleId);

    List<VehiclePersistenceEntity> findByStatus(VehicleStatus status);

    boolean existsByVehicleId(VehicleId vehicleId);

    boolean existsByPlateNumber(PlateNumber plateNumber);
}