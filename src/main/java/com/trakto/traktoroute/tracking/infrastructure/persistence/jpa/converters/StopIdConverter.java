package com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.converters;

import com.trakto.traktoroute.tracking.domain.model.valueobjects.StopId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter
public class StopIdConverter implements AttributeConverter<StopId, UUID> {

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