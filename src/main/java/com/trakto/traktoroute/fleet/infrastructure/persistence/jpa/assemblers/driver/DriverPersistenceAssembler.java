package com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.assemblers.driver;

import com.trakto.traktoroute.fleet.domain.model.aggregates.Driver;
import com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.entities.driver.DriverPersistenceEntity;

import java.util.Objects;

public final class DriverPersistenceAssembler {

    private DriverPersistenceAssembler() {}

    // To Domain
    public static Driver toDomainFromPersistence(DriverPersistenceEntity entity) {
        Objects.requireNonNull(entity);

        return Driver.reconstruct(
                entity.getDriverId(),
                entity.getProfileId(),
                entity.getLicenseNumber(),
                entity.getStatus()
        );
    }

    // To Persistence
    public static DriverPersistenceEntity toPersistenceFromDomain(Driver driver) {
        Objects.requireNonNull(driver);

        return new DriverPersistenceEntity(
                driver.getId(),
                driver.getProfileId(),
                driver.getLicenseNumber(),
                driver.getStatus()
        );
    }

    // Update Persistence
    public static void updatePersistenceFromDomain(Driver driver,
                                                    DriverPersistenceEntity entity) {
        Objects.requireNonNull(driver);
        Objects.requireNonNull(entity);

        entity.setLicenseNumber(
                driver.getLicenseNumber());

        entity.setStatus(
                driver.getStatus());
    }
}