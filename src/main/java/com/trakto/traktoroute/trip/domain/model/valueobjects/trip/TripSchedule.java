package com.trakto.traktoroute.trip.domain.model.valueobjects.trip;


import org.jspecify.annotations.Nullable;

import java.time.Instant;
import java.util.Objects;

public record TripSchedule(Instant scheduledAt,
                           @Nullable Instant startedAt,
                           @Nullable Instant completedAt) {

    public TripSchedule {

        if (scheduledAt == null) {
            throw new IllegalArgumentException("ScheduledAt cannot be null");
        }

        if (completedAt != null && startedAt == null) {
            throw new IllegalArgumentException("Trip cannot be completed without being started");
        }

        if (startedAt != null
                && completedAt != null
                && completedAt.isBefore(startedAt)) {
            throw new IllegalArgumentException("CompletedAt cannot be before startedAt");
        }
    }

    public static TripSchedule scheduled(Instant scheduledAt) {
        return new TripSchedule(
                scheduledAt,
                null,
                null
        );
    }

    public TripSchedule start(Instant startedAt) {
        Objects.requireNonNull(startedAt);

        if (this.startedAt != null) {
            throw new IllegalStateException("Trip has already started");
        }

        return new TripSchedule(
                scheduledAt,
                startedAt,
                completedAt
        );
    }

    public TripSchedule complete(Instant completedAt) {
        Objects.requireNonNull(completedAt);

        if (startedAt == null) {
            throw new IllegalStateException("Trip has not started");
        }

        return new TripSchedule(
                scheduledAt,
                startedAt,
                completedAt
        );
    }
}