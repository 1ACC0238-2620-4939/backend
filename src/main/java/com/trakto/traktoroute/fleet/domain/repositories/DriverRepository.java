package com.trakto.traktoroute.fleet.domain.repositories;

import com.trakto.traktoroute.fleet.domain.model.aggregates.Driver;
import com.trakto.traktoroute.fleet.domain.model.enums.DriverStatus;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.DriverId;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.LicenseNumber;

import java.util.List;
import java.util.Optional;

public interface DriverRepository {

    Driver save(Driver driver);

    Optional<Driver> findById(DriverId driverId);

    List<Driver> findAll();

    List<Driver> findByStatus(DriverStatus status);

    boolean existsById(DriverId driverId);

    boolean existsByLicenseNumber(LicenseNumber licenseNumber);
}