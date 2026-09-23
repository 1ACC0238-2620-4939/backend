package com.trakto.traktoroute.trip.infrastructure.persistence.jpa.converters.stop;

import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.StopId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter
public class StopIdPersistenceConverter
        implements AttributeConverter<StopId, UUID> {

    @Override
    public UUID convertToDatabaseColumn(StopId attribute) {
        return attribute == null
                ? null
                : attribute.value();
    }

    @Override
    public StopId convertToEntityAttribute(UUID dbData) {
        return dbData == null
                ? null
                : new StopId(dbData);
    }
}