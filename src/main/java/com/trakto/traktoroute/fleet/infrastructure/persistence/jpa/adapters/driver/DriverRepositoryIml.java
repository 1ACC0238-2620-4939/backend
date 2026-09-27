package com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.adapters.driver;

import com.trakto.traktoroute.fleet.domain.model.aggregates.Driver;
import com.trakto.traktoroute.fleet.domain.model.enums.DriverStatus;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.DriverId;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.LicenseNumber;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.ProfileId;
import com.trakto.traktoroute.fleet.domain.repositories.DriverRepository;
import com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.assemblers.driver.DriverPersistenceAssembler;
import com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.repositories.driver.DriverJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class DriverRepositoryIml implements DriverRepository {

    private final DriverJpaRepository driverJpaRepository;

    public DriverRepositoryIml(
            DriverJpaRepository driverJpaRepository) {
        this.driverJpaRepository = driverJpaRepository;
    }

    @Override
    public Driver save(Driver driver) {

        var existingEntity = driverJpaRepository.findByDriverId(
                        driver.getId());

        if (existingEntity.isPresent()) {
            var entity = existingEntity.get();

            DriverPersistenceAssembler.updatePersistenceFromDomain(
                    driver,
                    entity);

            var savedEntity = driverJpaRepository.save(entity);
            return DriverPersistenceAssembler.toDomainFromPersistence(savedEntity);
        }

        var newEntity = DriverPersistenceAssembler.toPersistenceFromDomain(driver);
        var savedEntity = driverJpaRepository.save(newEntity);
        return DriverPersistenceAssembler.toDomainFromPersistence(savedEntity);
    }

    @Override
    public Optional<Driver> findById(DriverId driverId) {
        return driverJpaRepository
                .findByDriverId(driverId)
                .map(DriverPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public Optional<Driver> findByProfileId(ProfileId profileId) {
        return driverJpaRepository
                .findByProfileId(profileId)
                .map(DriverPersistenceAssembler::toDomainFromPersistence);
    }

    @Override
    public List<Driver> findAll() {
        return driverJpaRepository
                .findAll()
                .stream()
                .map(DriverPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public List<Driver> findByStatus(DriverStatus status) {
        return driverJpaRepository
                .findByStatus(status)
                .stream()
                .map(DriverPersistenceAssembler::toDomainFromPersistence)
                .toList();
    }

    @Override
    public boolean existsById(DriverId driverId) {
        return driverJpaRepository.existsByDriverId(driverId);
    }

    @Override
    public boolean existsByProfileId(ProfileId profileId) {
        return driverJpaRepository.existsByProfileId(profileId);
    }

    @Override
    public boolean existsByLicenseNumber(LicenseNumber licenseNumber) {
        return driverJpaRepository.existsByLicenseNumber(licenseNumber);
    }
}