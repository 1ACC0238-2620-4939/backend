package com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.adapters.vehicle;

import com.trakto.traktoroute.fleet.domain.model.aggregates.Vehicle;
import com.trakto.traktoroute.fleet.domain.model.enums.VehicleStatus;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.PlateNumber;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleId;
import com.trakto.traktoroute.fleet.domain.repositories.VehicleRepository;
import com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.assemblers.vehicle.VehiclePersistenceAssembler;
import com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.repositories.vehicle.VehicleJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class VehicleRepositoryIml implements VehicleRepository {

    private final VehicleJpaRepository vehicleJpaRepository;

    public VehicleRepositoryIml(
            VehicleJpaRepository vehicleJpaRepository) {
        this.vehicleJpaRepository = vehicleJpaRepository;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {

        var existingEntity = vehicleJpaRepository.findByVehicleId(
                        vehicle.getId());

        if (existingEntity.isPresent()) {

            var entity = existingEntity.get();
            VehiclePersistenceAssembler.updatePersistenceFromDomain(
                    vehicle,
                    entity
            );

            var savedEntity = vehicleJpaRepository.save(entity);
            return VehiclePersistenceAssembler.toDomainFromPersistence(savedEntity);
        }

        var newEntity = VehiclePersistenceAssembler.toPersistenceFromDomain(vehicle);
        var savedEntity = vehicleJpaRepository.save(newEntity);
        return VehiclePersistenceAssembler.toDomainFromPersistence(savedEntity);
    }

    @Override
    public Optional<Vehicle> findById(VehicleId vehicleId) {
        return vehicleJpaRepository
                .findByVehicleId(vehicleId)
                .map(VehiclePersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleJpaRepository
                .findAll()
                .stream()
                .map(VehiclePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<Vehicle> findByStatus(VehicleStatus status) {
        return vehicleJpaRepository
                .findByStatus(status)
                .stream()
                .map(VehiclePersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public boolean existsById(VehicleId vehicleId) {
        return vehicleJpaRepository.existsByVehicleId(vehicleId);
    }

    @Override
    public boolean existsByPlateNumber(PlateNumber plateNumber) {
        return vehicleJpaRepository.existsByPlateNumber(plateNumber);
    }
}