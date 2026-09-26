package com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.converters.driver;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.DriverId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter
public class DriverIdConverter
        implements AttributeConverter<DriverId, UUID> {

    @Override
    public UUID convertToDatabaseColumn(DriverId attribute) {
        return attribute != null
                ? attribute.value()
                : null;
    }

    @Override
    public DriverId convertToEntityAttribute(UUID dbData) {
        return dbData != null
                ? new DriverId(dbData)
                : null;
    }
}