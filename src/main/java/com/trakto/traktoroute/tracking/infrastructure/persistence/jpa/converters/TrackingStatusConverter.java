package com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.converters;

import com.trakto.traktoroute.tracking.domain.model.enums.TrackingStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class TrackingStatusConverter
        implements AttributeConverter<TrackingStatus, String> {

    @Override
    public String convertToDatabaseColumn(TrackingStatus attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public TrackingStatus convertToEntityAttribute(String dbData) {
        return dbData == null ? null : TrackingStatus.valueOf(dbData);
    }
}