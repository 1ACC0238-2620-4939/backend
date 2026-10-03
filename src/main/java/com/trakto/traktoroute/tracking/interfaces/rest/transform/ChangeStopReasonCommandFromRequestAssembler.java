package com.trakto.traktoroute.tracking.interfaces.rest.transform;

import com.trakto.traktoroute.tracking.application.commands.ChangeStopReasonCommand;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.StopId;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TrackingId;
import com.trakto.traktoroute.tracking.interfaces.rest.resources.requests.ChangeStopReasonRequest;

import java.util.UUID;

public final class ChangeStopReasonCommandFromRequestAssembler {

    private ChangeStopReasonCommandFromRequestAssembler() {
    }

    public static ChangeStopReasonCommand toCommand(
            UUID trackingId,
            UUID stopId,
            ChangeStopReasonRequest request
    ) {
        return new ChangeStopReasonCommand(
                new TrackingId(trackingId),
                new StopId(stopId),
                request.reason()
        );
    }
}