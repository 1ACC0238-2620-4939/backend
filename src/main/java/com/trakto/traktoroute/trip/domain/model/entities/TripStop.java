package com.trakto.traktoroute.trip.domain.model.entities;

import com.trakto.traktoroute.trip.domain.model.enums.StopStatus;
import com.trakto.traktoroute.trip.domain.model.enums.StopType;
import com.trakto.traktoroute.trip.domain.model.valueobjects.TripLocation;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopId;
import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopSequence;

import java.time.Instant;
import java.util.Objects;


public class TripStop {
    private final StopId id;
    private final StopSequence sequence;
    private final TripLocation location;
    private final StopType type;
    private final Instant plannedArrivalAt;
    private StopStatus status;
    private Instant actualArrivalAt;

    public TripStop(StopId id,
                    StopSequence sequence,
                    TripLocation location,
                    StopType type,
                    Instant plannedArrivalAt) {
        this.id = Objects.requireNonNull(id,"tripStopId must not be null");
        this.sequence = Objects.requireNonNull(sequence,"sequence must not be null");
        this.location = Objects.requireNonNull(location,"location must not be null");
        this.type = Objects.requireNonNull(type,"type must not be null");
        this.status = StopStatus.PENDING;
        this.plannedArrivalAt = Objects.requireNonNull(plannedArrivalAt,"plannedArrivalAt must not be null");
    }
}
