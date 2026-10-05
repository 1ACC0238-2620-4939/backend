package com.trakto.traktoroute.trip;

import com.trakto.traktoroute.trip.domain.model.aggregates.Trip;
import com.trakto.traktoroute.trip.domain.model.enums.TripStatus;
import com.trakto.traktoroute.trip.domain.model.valueobjects.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.Instant;
import static org.junit.jupiter.api.Assertions.*;

class TripLifecycleTests {
    private static final Instant START = Instant.parse("2026-10-05T12:00:00Z");
    private Trip scheduledTrip() {
        return Trip.create(new DriverId(java.util.UUID.randomUUID()), new VehicleId(java.util.UUID.randomUUID()),
                new TripLocation("Test origin", BigDecimal.ZERO, BigDecimal.ZERO),
                new TripLocation("Test destination", BigDecimal.ONE, BigDecimal.ONE),
                TripSchedule.scheduled(START),
                new TripRoutePlan(BigDecimal.TEN, 60, "test-route", START.minusSeconds(60)));
    }
    @Test void newTripIsScheduled() { assertEquals(TripStatus.SCHEDULED, scheduledTrip().getStatus()); }
    @Test void scheduledTripCanStartAndComplete() {
        var trip = scheduledTrip(); trip.start(START); trip.complete(START.plusSeconds(3600));
        assertEquals(TripStatus.COMPLETED, trip.getStatus());
        assertEquals(START.plusSeconds(3600), trip.getSchedule().completedAt());
    }
    @Test void tripCannotCompleteBeforeStarting() {
        var trip = scheduledTrip(); assertThrows(IllegalStateException.class, () -> trip.complete(START));
        assertEquals(TripStatus.SCHEDULED, trip.getStatus());
    }
    @Test void cancelledTripCannotStart() {
        var trip = scheduledTrip(); trip.cancel(START);
        assertThrows(IllegalStateException.class, () -> trip.start(START.plusSeconds(1)));
    }
    @Test void completedTripCannotBeCancelled() {
        var trip = scheduledTrip(); trip.start(START); trip.complete(START.plusSeconds(10));
        assertThrows(IllegalStateException.class, () -> trip.cancel(START.plusSeconds(20)));
    }
    @Test void completionBeforeStartDoesNotChangeStatus() {
        var trip = scheduledTrip(); trip.start(START);
        assertThrows(IllegalArgumentException.class, () -> trip.complete(START.minusSeconds(1)));
        assertEquals(TripStatus.IN_PROGRESS, trip.getStatus());
    }
    @Test void duplicateStartIsRejected() {
        var trip = scheduledTrip(); trip.start(START);
        assertThrows(IllegalStateException.class, () -> trip.start(START.plusSeconds(1)));
    }
    @Test void invalidCoordinatesAreRejected() {
        assertThrows(IllegalArgumentException.class, () -> new TripLocation("Lima", BigDecimal.valueOf(91), BigDecimal.ZERO));
    }
    @Test void nonPositiveRouteDistanceIsRejected() {
        assertThrows(IllegalArgumentException.class, () -> new TripRoutePlan(BigDecimal.ZERO, 60, "route", START));
    }
}
