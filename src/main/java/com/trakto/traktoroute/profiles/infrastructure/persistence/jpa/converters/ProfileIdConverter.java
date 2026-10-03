package com.trakto.traktoroute.profiles.infrastructure.persistence.jpa.converters;

import com.trakto.traktoroute.profiles.domain.model.valueobjects.ProfileId;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = false)
public class ProfileIdConverter
        implements AttributeConverter<ProfileId, UUID> {

    @Override
    public UUID convertToDatabaseColumn(ProfileId attribute) {
        return attribute == null ? null : attribute.value();
    }

    @Override
    public ProfileId convertToEntityAttribute(UUID dbData) {
        return dbData == null ? null : new ProfileId(dbData);
    }
}