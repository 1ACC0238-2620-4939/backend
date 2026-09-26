package com.trakto.traktoroute.fleet.domain.model.events.driver;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.DriverId;

import java.time.Instant;

public record DriverActivatedEvent(DriverId driverId,
                                   Instant occurredAt) {
    public DriverActivatedEvent {
        if (driverId == null) {
            throw new IllegalArgumentException("Driver id cannot be null");}

        if (occurredAt == null) {
            throw new IllegalArgumentException("Occurred at cannot be null");}
    }
}