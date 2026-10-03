package com.trakto.traktoroute.trip.domain.model.valueobjects;

import org.jspecify.annotations.Nullable;

import java.time.Instant;
import java.util.Objects;

public record TripSchedule(
        Instant scheduledAt,
        @Nullable Instant startedAt,
        @Nullable Instant completedAt,
        @Nullable Instant cancelledAt
) {

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

        if (startedAt != null
                && cancelledAt != null
                && cancelledAt.isBefore(startedAt)) {

            throw new IllegalArgumentException("CancelledAt cannot be before startedAt");
        }

        if (completedAt != null && cancelledAt != null) {
            throw new IllegalArgumentException("Trip cannot be both completed and cancelled");
        }
    }

    public static TripSchedule scheduled(Instant scheduledAt) {
        return new TripSchedule(
                scheduledAt,
                null,
                null,
                null
        );
    }

    public TripSchedule start(Instant startedAt) {
        Objects.requireNonNull(startedAt);

        if (this.startedAt != null) {
            throw new IllegalStateException("Trip has already started");
        }

        if (cancelledAt != null) {
            throw new IllegalStateException("Cancelled trip cannot be started");
        }

        return new TripSchedule(
                scheduledAt,
                startedAt,
                completedAt,
                cancelledAt
        );
    }

    public TripSchedule complete(Instant completedAt) {
        Objects.requireNonNull(completedAt);

        if (startedAt == null) {
            throw new IllegalStateException("Trip has not started");
        }

        if (this.completedAt != null) {
            throw new IllegalStateException("Trip has already been completed");
        }

        if (cancelledAt != null) {
            throw new IllegalStateException("Cancelled trip cannot be completed");
        }

        return new TripSchedule(
                scheduledAt,
                startedAt,
                completedAt,
                cancelledAt
        );
    }

    public TripSchedule cancel(Instant cancelledAt) {
        Objects.requireNonNull(cancelledAt);

        if (this.cancelledAt != null) {
            throw new IllegalStateException("Trip has already been cancelled");
        }

        if (completedAt != null) {
            throw new IllegalStateException("Completed trip cannot be cancelled");
        }

        if (startedAt != null && cancelledAt.isBefore(startedAt)) {

            throw new IllegalArgumentException("Cancellation cannot be before trip start");
        }

        return new TripSchedule(
                scheduledAt,
                startedAt,
                completedAt,
                cancelledAt
        );
    }
}