package com.trakto.traktoroute.trip.infrastructure.persistence.jpa.embeddables;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
public class TripLocationPersistenceEmbeddable {

    @Column(name = "address", nullable = false, length = 255)
    private String address;

    @Column(name = "latitude", nullable = false, precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false, precision = 9, scale = 6)
    private BigDecimal longitude;

    public TripLocationPersistenceEmbeddable(String address,
                                             BigDecimal latitude,
                                             BigDecimal longitude) {
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}