package com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.converters;

import com.trakto.traktoroute.tracking.domain.model.valueobjects.TripReferenceId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter
public class TripReferenceIdConverter
        implements AttributeConverter<TripReferenceId, UUID> {

    @Override
    public UUID convertToDatabaseColumn(TripReferenceId attribute) {
        return attribute == null ? null : attribute.value();
    }

    @Override
    public TripReferenceId convertToEntityAttribute(UUID dbData) {
        return dbData == null ? null : new TripReferenceId(dbData);
    }
}