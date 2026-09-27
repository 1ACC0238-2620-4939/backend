package com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.converters.driver;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.driver.ProfileId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter
public class ProfileIdConverter
        implements AttributeConverter<ProfileId, UUID> {

    @Override
    public UUID convertToDatabaseColumn(ProfileId attribute) {
        return attribute != null
                ? attribute.value()
                : null;
    }

    @Override
    public ProfileId convertToEntityAttribute(UUID dbData) {
        return dbData != null
                ? new ProfileId(dbData)
                : null;
    }
}