package com.trakto.traktoroute.tracking.infrastructure.persistence.jpa.converters;

import com.trakto.traktoroute.tracking.domain.model.enums.StopReason;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class StopReasonConverter
        implements AttributeConverter<StopReason, String> {

    @Override
    public String convertToDatabaseColumn(StopReason attribute) {
        return attribute == null ? null : attribute.name();
    }

    @Override
    public StopReason convertToEntityAttribute(String dbData) {
        return dbData == null ? null : StopReason.valueOf(dbData);
    }
}