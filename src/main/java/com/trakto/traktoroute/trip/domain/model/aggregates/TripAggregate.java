package com.trakto.traktoroute.trip.domain.model.aggregates;

import com.trakto.traktoroute.shared.domain.models.aggregates.AbstractDomainAggregateRoot;
import com.trakto.traktoroute.trip.domain.model.valueobjects.*;
import com.trakto.traktoroute.trip.domain.model.valueobjects.TripLocation;

public class TripAggregate extends AbstractDomainAggregateRoot<TripAggregate> {

    private TripId tripId;
    private TripCode tripCode;
    private TripLocation tripOrigin;
    private TripLocation tripDestination;
    private TripSchedule tripSchedule;
    private TripRoutePlan tripRoutePlan;

}
