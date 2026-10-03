package com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.converters;

import com.trakto.traktoroute.tracking.domain.model.valueobjects.TrackingId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter
public class TrackingIdConverter implements AttributeConverter<TrackingId, UUID> {

    @Override
    public UUID convertToDatabaseColumn(TrackingId attribute) {
        return attribute == null ? null : attribute.value();
    }

    @Override
    public TrackingId convertToEntityAttribute(UUID dbData) {
        return dbData == null ? null : new TrackingId(dbData);
    }
}