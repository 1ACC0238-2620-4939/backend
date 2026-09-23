package com.trakto.traktoroute.trip.domain.model.entities;


import com.trakto.traktoroute.trip.domain.model.enums.StopReason;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopLocation;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.TripInstant;
import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripLocation;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopId;
import jakarta.annotation.Nullable;
import lombok.Getter;

import java.util.Objects;

@Getter
public class TripStop {
    private final StopId id;
    private final StopLocation location;
    private final TripInstant startedAt;

    @Nullable
    private TripInstant endedAt;

    private final StopReason reason;

    public TripStop(
            StopId id,
            StopLocation location,
            TripInstant startedAt,
            @Nullable TripInstant endedAt,
            StopReason reason) {
        this.id = Objects.requireNonNull(id);
        this.location = Objects.requireNonNull(location);
        this.startedAt = Objects.requireNonNull(startedAt);
        this.endedAt = endedAt;
        this.reason = Objects.requireNonNull(reason);
    }

    public static TripStop start(
            StopLocation location,
            TripInstant startedAt,
            StopReason reason) {
        return new TripStop(
                StopId.generate(),
                location,
                startedAt,
                null,
                reason
        );
    }

    public static TripStop reconstitute(
            StopId id,
            StopLocation location,
            TripInstant startedAt,
            @Nullable TripInstant endedAt,
            StopReason reason) {
        return new TripStop(
                id,
                location,
                startedAt,
                endedAt,
                reason
        );
    }

    public void finish(TripInstant endedAt) {

        Objects.requireNonNull(endedAt);

        if (this.endedAt != null) {
            throw new IllegalStateException("Stop has already finished");
        }
        if (endedAt.isBefore(startedAt)) {
            throw new IllegalArgumentException("EndedAt cannot be before startedAt");
        }
        this.endedAt = endedAt;
    }

    public boolean isOpen() {
        return endedAt == null;
    }
}