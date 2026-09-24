package com.trakto.traktoroute.trip.infrastructure.persistence.jpa.converters.trips;

import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.VehicleId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter
public class VehicleIdPersistenceConverter
        implements AttributeConverter<VehicleId, UUID> {

    @Override
    public UUID convertToDatabaseColumn(VehicleId attribute) {
        return attribute == null
                ? null
                : attribute.value();
    }

    @Override
    public VehicleId convertToEntityAttribute(UUID dbData) {
        return dbData == null
                ? null
                : new VehicleId(dbData);
    }
}