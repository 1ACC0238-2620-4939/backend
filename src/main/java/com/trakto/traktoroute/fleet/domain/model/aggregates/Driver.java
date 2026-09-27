package com.trakto.traktoroute.fleet.domain.model.aggregates;

import com.trakto.traktoroute.fleet.domain.model.enums.DriverStatus;
import com.trakto.traktoroute.fleet.domain.model.events.driver.DriverActivatedEvent;
import com.trakto.traktoroute.fleet.domain.model.events.driver.DriverCreatedEvent;
import com.trakto.traktoroute.fleet.domain.model.events.driver.DriverDeactivatedEvent;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.DriverId;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.LicenseNumber;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.ProfileId;
import com.trakto.traktoroute.shared.domain.models.aggregates.AbstractDomainAggregateRoot;
import lombok.Getter;

import java.time.Instant;
import java.util.Objects;

@Getter
public class Driver extends AbstractDomainAggregateRoot<Driver> {

    private final DriverId id;
    private final ProfileId profileId;
    private LicenseNumber licenseNumber;
    private DriverStatus status;

    private Driver(
            DriverId id,
            ProfileId profileId,
            LicenseNumber licenseNumber,
            DriverStatus status
    ) {
        this.id = Objects.requireNonNull(id, "Driver id cannot be null");
        this.profileId = Objects.requireNonNull(profileId, "Profile id cannot be null");
        this.licenseNumber = Objects.requireNonNull(licenseNumber, "License number cannot be null");
        this.status = Objects.requireNonNull(status, "Driver status cannot be null");
    }

    public static Driver create(ProfileId profileId,
                                LicenseNumber licenseNumber) {
        var driver = new Driver(
                DriverId.generate(),
                profileId,
                licenseNumber,
                DriverStatus.ACTIVE
        );

        driver.registerDomainEvent(
                new DriverCreatedEvent(
                        driver.id,
                        Instant.now()
                )
        );

        return driver;
    }

    public static Driver reconstruct(
            DriverId id,
            ProfileId profileId,
            LicenseNumber licenseNumber,
            DriverStatus status
    ) {
        return new Driver(
                id,
                profileId,
                licenseNumber,
                status
        );
    }

    public void activate() {
        if (status == DriverStatus.ACTIVE) {
            throw new IllegalStateException("Driver is already active");
        }

        status = DriverStatus.ACTIVE;

        registerDomainEvent(
                new DriverActivatedEvent(
                        id,
                        Instant.now()
                )
        );
    }

    public void deactivate() {
        if (status == DriverStatus.INACTIVE) {
            throw new IllegalStateException("Driver is already inactive");
        }

        status = DriverStatus.INACTIVE;

        registerDomainEvent(
                new DriverDeactivatedEvent(
                        id,
                        Instant.now()
                )
        );
    }

    public void changeLicenseNumber(
            LicenseNumber licenseNumber
    ) {
        this.licenseNumber = Objects.requireNonNull(licenseNumber, "License number cannot be null");
    }

    public boolean isActive() {
        return status == DriverStatus.ACTIVE;
    }
}