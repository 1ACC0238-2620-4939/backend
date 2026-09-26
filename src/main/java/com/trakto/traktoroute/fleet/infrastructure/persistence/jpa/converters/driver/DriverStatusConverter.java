package com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.converters.driver;

import com.trakto.traktoroute.fleet.domain.model.enums.DriverStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class DriverStatusConverter
        implements AttributeConverter<DriverStatus, String> {

    @Override
    public String convertToDatabaseColumn(DriverStatus attribute) {
        return attribute != null
                ? attribute.name()
                : null;
    }

    @Override
    public DriverStatus convertToEntityAttribute(String dbData) {
        return dbData != null
                ? DriverStatus.valueOf(dbData)
                : null;
    }
}