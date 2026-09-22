package com.trakto.traktoroute.trip.domain.model.valueobjects.stop;

public record StopSequence(int sequence) {
    public StopSequence {
        if (sequence <= 0)
            throw new IllegalArgumentException("Sequence cannot be negative");
    }
}
