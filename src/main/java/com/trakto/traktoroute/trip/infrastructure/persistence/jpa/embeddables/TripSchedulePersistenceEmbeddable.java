package com.trakto.traktoroute.trip.infrastructure.persistence.jpa.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class TripSchedulePersistenceEmbeddable {

    @Column(name = "scheduled_at", nullable = false)
    private Instant scheduledAt;

    @Column(name = "started_at")
    private Instant startedAt;

    @Column(name = "completed_at")
    private Instant completedAt;

    @Column(name = "cancelled_at")
    private Instant cancelledAt;

    public TripSchedulePersistenceEmbeddable(Instant scheduledAt,
                                             Instant startedAt,
                                             Instant completedAt,
                                             Instant cancelledAt) {
        this.scheduledAt = scheduledAt;
        this.startedAt = startedAt;
        this.completedAt = completedAt;
        this.cancelledAt = cancelledAt;
    }
}