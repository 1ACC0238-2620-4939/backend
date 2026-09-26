package com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.converters.driver;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.PhoneNumber;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PhoneNumberConverter
        implements AttributeConverter<PhoneNumber, String> {

    @Override
    public String convertToDatabaseColumn(PhoneNumber attribute) {
        return attribute != null
                ? attribute.value()
                : null;
    }

    @Override
    public PhoneNumber convertToEntityAttribute(String dbData) {
        return dbData != null
                ? new PhoneNumber(dbData)
                : null;
    }
}