package com.trakto.traktoroute.fleet.application.queries.driver;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.ProfileId;

public record GetDriverByProfileIdQuery(ProfileId profileId) {
}