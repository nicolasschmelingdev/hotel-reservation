package com.ntconsult.hotelreservation.domain.model.enums;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class RoomTypeConverter implements AttributeConverter<RoomType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(RoomType roomType) {
        if (roomType == null) {
            return null;
        }
        return roomType.getValue();
    }

    @Override
    public RoomType convertToEntityAttribute(Integer dbData) {
        if (dbData == null) {
            return null;
        }
        return RoomType.fromValue(dbData);
    }
}

