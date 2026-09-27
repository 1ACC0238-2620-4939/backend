package com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.entities.driver;

import com.trakto.traktoroute.fleet.domain.model.enums.DriverStatus;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.DriverId;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.LicenseNumber;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.ProfileId;
import com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.converters.driver.DriverIdConverter;
import com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.converters.driver.DriverStatusConverter;
import com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.converters.driver.LicenseNumberConverter;
import com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.converters.driver.ProfileIdConverter;
import com.trakto.traktoroute.shared.infrastructure.persistence.jpa.entities.AuditableAbstractPersistenceEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Entity
@Table(name = "drivers")
@NoArgsConstructor
public class DriverPersistenceEntity extends AuditableAbstractPersistenceEntity {

    @Convert(converter = DriverIdConverter.class)
    @Column(name = "driver_id",
            nullable = false,
            updatable = false,
            unique = true)
    private DriverId driverId;

    @Convert(converter = ProfileIdConverter.class)
    @Column(name = "profile_id",
            nullable = false,
            updatable = false,
            unique = true)
    private ProfileId profileId;

    @Setter
    @Convert(converter = LicenseNumberConverter.class)
    @Column(name = "license_number",
            nullable = false,
            length = 9,
            unique = true)
    private LicenseNumber licenseNumber;

    @Setter
    @Convert(converter = DriverStatusConverter.class)
    @Column(name = "status",
            nullable = false,
            length = 20)
    private DriverStatus status;

    public DriverPersistenceEntity(
            DriverId driverId,
            ProfileId profileId,
            LicenseNumber licenseNumber,
            DriverStatus status
    ) {
        this.driverId = Objects.requireNonNull(driverId, "Driver id cannot be null");
        this.profileId = Objects.requireNonNull(profileId, "Profile id cannot be null");
        this.licenseNumber = Objects.requireNonNull(licenseNumber, "License number cannot be null");
        this.status = Objects.requireNonNull(status, "Driver status cannot be null");
    }
}