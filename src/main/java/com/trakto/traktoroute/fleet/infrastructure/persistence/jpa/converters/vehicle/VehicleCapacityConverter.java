package com.trakto.traktoroute.fleet.infrastructure.persistence.jpa.converters.vehicle;

import com.trakto.traktoroute.fleet.domain.model.valueobjects.vehicle.VehicleCapacity;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

import java.math.BigDecimal;

@Converter
public class VehicleCapacityConverter
        implements AttributeConverter<VehicleCapacity, BigDecimal> {

    @Override
    public BigDecimal convertToDatabaseColumn(VehicleCapacity attribute) {
        return attribute != null
                ? attribute.value()
                : null;
    }

    @Override
    public VehicleCapacity convertToEntityAttribute(BigDecimal dbData) {
        return dbData != null
                ? new VehicleCapacity(dbData)
                : null;
    }
}