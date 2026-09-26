package com.trakto.traktoroute.fleet.domain.repositories;

import com.trakto.traktoroute.fleet.domain.model.aggregates.Vehicle;
import com.trakto.traktoroute.fleet.domain.model.enums.VehicleStatus;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.PlateNumber;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleId;

import java.util.List;
import java.util.Optional;

public interface VehicleRepository {

    Vehicle save(Vehicle vehicle);

    Optional<Vehicle> findById(VehicleId vehicleId);

    List<Vehicle> findAll();

    List<Vehicle> findByStatus(VehicleStatus status);

    boolean existsById(VehicleId vehicleId);

    boolean existsByPlateNumber(PlateNumber plateNumber);
}