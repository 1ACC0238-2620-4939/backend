package com.trakto.traktoroute.trip.infrastructure.persistence.jpa.converters.stop;

import com.trakto.traktoroute.trip.domain.model.valueobjects.stop.TripInstant;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.time.Instant;

@Converter
public class TripInstantPersistenceConverter
        implements AttributeConverter<TripInstant, Instant> {

    @Override
    public Instant convertToDatabaseColumn(TripInstant attribute) {
        return attribute == null
                ? null
                : attribute.value();
    }

    @Override
    public TripInstant convertToEntityAttribute(Instant dbData) {
        return dbData == null
                ? null
                : new TripInstant(dbData);
    }
}