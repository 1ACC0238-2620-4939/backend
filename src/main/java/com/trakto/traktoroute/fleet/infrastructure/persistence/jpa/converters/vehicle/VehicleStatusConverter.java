package com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.converters.vehicle;

import com.trakto.traktoroute.fleet.domain.model.enums.VehicleStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class VehicleStatusConverter
        implements AttributeConverter<VehicleStatus, String> {

    @Override
    public String convertToDatabaseColumn(VehicleStatus attribute) {
        return attribute != null
                ? attribute.name()
                : null;
    }

    @Override
    public VehicleStatus convertToEntityAttribute(String dbData) {
        return dbData != null
                ? VehicleStatus.valueOf(dbData)
                : null;
    }
}