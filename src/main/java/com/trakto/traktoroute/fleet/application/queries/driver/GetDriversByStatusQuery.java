package com.trakto.traktoroute.fleet.application.queries.driver;

import com.trakto.traktoroute.fleet.domain.model.enums.DriverStatus;

public record GetDriversByStatusQuery(DriverStatus status) {
}