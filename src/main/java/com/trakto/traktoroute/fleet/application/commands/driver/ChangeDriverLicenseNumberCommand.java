package com.trakto.traktoroute.fleet.application.commands.driver;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.DriverId;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.LicenseNumber;

public record ChangeDriverLicenseNumberCommand(DriverId driverId,
                                               LicenseNumber licenseNumber) {
}