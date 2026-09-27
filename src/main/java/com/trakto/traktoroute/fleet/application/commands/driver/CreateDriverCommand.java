package com.trakto.traktoroute.fleet.application.commands.driver;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.LicenseNumber;
import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.ProfileId;

public record CreateDriverCommand(ProfileId profileId,
                                  LicenseNumber licenseNumber) {
}