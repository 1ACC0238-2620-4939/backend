package com.trakto.traktoroute.tracking.interfaces.rest.transform;

import com.trakto.traktoroute.tracking.domain.model.aggregates.Tracking;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.GeoLocation;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.PositionReport;
import com.trakto.traktoroute.tracking.interfaces.rest.resources.responses.TrackingResponse;
import com.trakto.traktoroute.tracking.interfaces.rest.resources.responses.TrackingStopResponse;

import java.util.List;

public final class TrackingResponseFromEntityAssembler {

    private TrackingResponseFromEntityAssembler() {
    }

    public static TrackingResponse toResourceFromEntity(
            Tracking tracking
    ) {
        PositionReport lastPositionReport =
                tracking.getLastPositionReport();

        GeoLocation stationaryLocation =
                tracking.getStationaryLocation();

        List<TrackingStopResponse> stops = tracking.getStops().stream()
                .map(TrackingStopResponseFromEntityAssembler::toResourceFromEntity)
                .toList();

        return new TrackingResponse(
                tracking.getTrackingId().value(),
                tracking.getTripReferenceId().value(),
                tracking.getStatus().name(),

                lastPositionReport != null
                        ? lastPositionReport.location().latitude()
                        : null,

                lastPositionReport != null
                        ? lastPositionReport.location().longitude()
                        : null,

                lastPositionReport != null
                        ? lastPositionReport.recordedAt()
                        : null,

                stationaryLocation != null
                        ? stationaryLocation.latitude()
                        : null,

                stationaryLocation != null
                        ? stationaryLocation.longitude()
                        : null,

                tracking.getStationarySince(),
                stops
        );
    }
}