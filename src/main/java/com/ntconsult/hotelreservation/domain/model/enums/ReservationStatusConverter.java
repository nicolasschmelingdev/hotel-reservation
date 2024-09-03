package com.ntconsult.hotelreservation.domain.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class ReservationStatusConverter implements AttributeConverter<ReservationStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ReservationStatus attribute) {
        return attribute != null ? attribute.getValue() : null;
    }

    @Override
    public ReservationStatus convertToEntityAttribute(Integer dbData) {
        return dbData != null ? ReservationStatus.fromValue(dbData) : null;
    }
}
