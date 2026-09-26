package com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.converters.driver;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.LicenseNumber;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class LicenseNumberConverter
        implements AttributeConverter<LicenseNumber, String> {

    @Override
    public String convertToDatabaseColumn(LicenseNumber attribute) {
        return attribute != null
                ? attribute.value()
                : null;
    }

    @Override
    public LicenseNumber convertToEntityAttribute(String dbData) {
        return dbData != null
                ? new LicenseNumber(dbData)
                : null;
    }
}