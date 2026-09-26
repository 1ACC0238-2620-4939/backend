package com.trakto.traktoroute.fleet.domain.model.events.vehicle;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleId;

import java.time.Instant;

public record VehicleActivatedEvent(VehicleId vehicleId,
                                    Instant occurredAt) {
    public VehicleActivatedEvent {
        if (vehicleId == null) {
            throw new IllegalArgumentException("Vehicle id cannot be null");}

        if (occurredAt == null) {
            throw new IllegalArgumentException("Occurred at cannot be null");}
    }
}