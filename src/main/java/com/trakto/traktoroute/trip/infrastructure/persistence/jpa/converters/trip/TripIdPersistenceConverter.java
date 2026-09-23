package com.trakto.traktoroute.trip.infrastructure.persistence.jpa.converters.trip;

import com.trakto.traktoroute.trip.domain.model.valueobjects.trip.TripId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter
public class TripIdPersistenceConverter
        implements AttributeConverter<TripId, UUID> {

    @Override
    public UUID convertToDatabaseColumn(TripId attribute) {
        return attribute == null
                ? null
                : attribute.value();
    }

    @Override
    public TripId convertToEntityAttribute(UUID dbData) {
        return dbData == null
                ? null
                : new TripId(dbData);
    }
}