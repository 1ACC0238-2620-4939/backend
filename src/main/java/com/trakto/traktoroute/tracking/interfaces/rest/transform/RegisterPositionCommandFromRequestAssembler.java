package com.trakto.traktoroute.tracking.interfaces.rest.transform;

import com.trakto.traktoroute.tracking.application.commands.RegisterPositionCommand;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.GeoLocation;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.PositionReport;
import com.trakto.traktoroute.tracking.domain.model.valueobjects.TrackingId;
import com.trakto.traktoroute.tracking.interfaces.rest.resources.requests.RegisterPositionRequest;

import java.util.UUID;

public final class RegisterPositionCommandFromRequestAssembler {

    private RegisterPositionCommandFromRequestAssembler() {
    }

    public static RegisterPositionCommand toCommand(
            UUID trackingId,
            RegisterPositionRequest request
    ) {
        GeoLocation location = new GeoLocation(
                request.latitude(),
                request.longitude()
        );

        PositionReport positionReport = new PositionReport(
                location,
                request.recordedAt()
        );

        return new RegisterPositionCommand(
                new TrackingId(trackingId),
                positionReport
        );
    }
}