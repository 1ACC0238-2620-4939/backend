package com.trakto.traktoroute.profiles.infrastructure.persistence.jpa.converters;

import com.trakto.traktoroute.profiles.domain.model.valueobjects.UserReferenceId;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter(autoApply = false)
public class UserReferenceIdConverter
        implements AttributeConverter<UserReferenceId, UUID> {

    @Override
    public UUID convertToDatabaseColumn(UserReferenceId attribute) {
        return attribute == null ? null : attribute.value();
    }

    @Override
    public UserReferenceId convertToEntityAttribute(UUID dbData) {
        return dbData == null ? null : new UserReferenceId(dbData);
    }
}