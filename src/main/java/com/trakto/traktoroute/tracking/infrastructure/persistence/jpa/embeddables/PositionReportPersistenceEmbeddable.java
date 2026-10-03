package com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.embeddables;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PositionReportPersistenceEmbeddable {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(
                    name = "latitude",
                    column = @Column(name = "position_latitude")
            ),
            @AttributeOverride(
                    name = "longitude",
                    column = @Column(name = "position_longitude")
            )
    })
    private GeoLocationPersistenceEmbeddable location;

    @Column(name = "position_recorded_at")
    private Instant recordedAt;
}