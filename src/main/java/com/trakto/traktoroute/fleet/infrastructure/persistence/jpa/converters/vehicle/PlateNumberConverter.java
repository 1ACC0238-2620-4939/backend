package com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.converters.vehicle;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.PlateNumber;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PlateNumberConverter
        implements AttributeConverter<PlateNumber, String> {

    @Override
    public String convertToDatabaseColumn(PlateNumber attribute) {
        return attribute != null
                ? attribute.value()
                : null;
    }

    @Override
    public PlateNumber convertToEntityAttribute(String dbData) {
        return dbData != null
                ? new PlateNumber(dbData)
                : null;
    }
}