package com.trakto.traktoroute.trip.infrastructure.persistence.jpa.converters;

import com.trakto.traktoroute.trip.domain.model.valueobjects.DriverId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter
public class DriverIdPersistenceConverter implements AttributeConverter<DriverId, UUID> {

    @Override
    public UUID convertToDatabaseColumn(DriverId attribute) {
        return attribute == null
                ? null
                : attribute.value();
    }

    @Override
    public DriverId convertToEntityAttribute(UUID dbData) {
        return dbData == null
                ? null
                : new DriverId(dbData);
    }
}