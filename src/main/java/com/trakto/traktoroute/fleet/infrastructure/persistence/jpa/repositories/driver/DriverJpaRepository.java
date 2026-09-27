package com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.repositories.driver;

import com.trakto.traktoroute.fleet.domain.model.enums.DriverStatus;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.DriverId;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.LicenseNumber;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.ProfileId;
import com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.entities.driver.DriverPersistenceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DriverJpaRepository extends JpaRepository<DriverPersistenceEntity, Long> {

    Optional<DriverPersistenceEntity> findByDriverId(DriverId driverId);

    Optional<DriverPersistenceEntity> findByProfileId(ProfileId profileId);

    List<DriverPersistenceEntity> findByStatus(DriverStatus status);

    boolean existsByDriverId(DriverId driverId);

    boolean existsByProfileId(ProfileId profileId);

    boolean existsByLicenseNumber(LicenseNumber licenseNumber);
}