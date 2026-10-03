package com.trakto.traktoroute.tracking.domain.model.entities;

import com.trakto.traktoroute.tracking.domain.model.valueobjects.GeoLocation;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.StopId;
import com.trakto.traktoroute.tracking.domain.model.enums.StopReason;
import lombok.Getter;

import java.time.Instant;

@Getter
public class TrackingStop {

    private final StopId stopId;
    private final GeoLocation location;
    private final Instant startedAt;

    private Instant endedAt;
    private StopReason reason;

    // Crear una parada detectada automáticamente
    public TrackingStop(GeoLocation location, Instant startedAt) {
        this(
                StopId.generate(),
                location,
                startedAt,
                null,
                StopReason.UNKNOWN
        );
    }

    // Reconstruir una parada desde persistencia
    public TrackingStop(
            StopId stopId,
            GeoLocation location,
            Instant startedAt,
            Instant endedAt,
            StopReason reason
    ) {
        if (stopId == null) {
            throw new IllegalArgumentException("Stop ID is required");
        }

        if (location == null) {
            throw new IllegalArgumentException("Location is required");
        }

        if (startedAt == null) {
            throw new IllegalArgumentException("Start time is required");
        }

        if (reason == null) {
            throw new IllegalArgumentException("Stop reason is required");
        }

        if (endedAt != null && endedAt.isBefore(startedAt)) {
            throw new IllegalArgumentException(
                    "End time cannot be before start time"
            );
        }

        this.stopId = stopId;
        this.location = location;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
        this.reason = reason;
    }

    public void finish(Instant endedAt) {
        if (!isOpen()) {
            throw new IllegalStateException("Stop is already finished");
        }

        if (endedAt == null) {
            throw new IllegalArgumentException("End time is required");
        }

        if (endedAt.isBefore(startedAt)) {
            throw new IllegalArgumentException(
                    "End time cannot be before start time");
        }

        this.endedAt = endedAt;
    }

    public void changeReason(StopReason reason) {
        if (reason == null) {
            throw new IllegalArgumentException("Stop reason is required");
        }

        this.reason = reason;
    }

    public boolean isOpen() {
        return endedAt == null;
    }



    @Override
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }

        if (!(object instanceof TrackingStop other)) {
            return false;
        }

        return stopId.equals(other.stopId);
    }

    @Override
    public int hashCode() {
        return stopId.hashCode();
    }
}