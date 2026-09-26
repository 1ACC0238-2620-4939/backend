package com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.converters.vehicle;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleId;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.util.UUID;

@Converter
public class VehicleIdConverter
        implements AttributeConverter<VehicleId, UUID> {

    @Override
    public UUID convertToDatabaseColumn(VehicleId attribute) {
        return attribute != null
                ? attribute.value()
                : null;
    }

    @Override
    public VehicleId convertToEntityAttribute(UUID dbData) {
        return dbData != null
                ? new VehicleId(dbData)
                : null;
    }
}