package com.trakto.traktoroute.trip.infrastructure.persistence.jpa.embeddables.stop;

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
@AllArgsConstructor
public class StopLocationPersistenceEmbeddable {

    private BigDecimal latitude;

    private BigDecimal longitude;
}