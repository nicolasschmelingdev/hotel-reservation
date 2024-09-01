package com.ntconsult.hotelreservation.util;

import com.ntconsult.hotelreservation.domain.model.QRoom;
import com.ntconsult.hotelreservation.domain.model.Room;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Component;

@Component
public class RoomSpecifications {

    public BooleanExpression byRoomNumber(String roomNumber) {
        if (roomNumber == null || roomNumber.isEmpty()) {
            return null;
        }
        return QRoom.room.roomNumber.containsIgnoreCase(roomNumber);
    }

    public BooleanExpression byHotelId(Long hotelId) {
        if (hotelId == null) {
            return null;
        }
        return QRoom.room.hotel.id.eq(hotelId);
    }

    public BooleanExpression byRoomType(String roomType) {
        if (roomType == null || roomType.isEmpty()) {
            return null;
        }
        return QRoom.room.roomType.eq(Room.RoomType.valueOf(roomType.toUpperCase()));
    }

    public BooleanExpression combineFilters(String roomNumber, Long hotelId, String roomType) {
        BooleanExpression predicate = QRoom.room.isNotNull();

        BooleanExpression roomNumberFilter = byRoomNumber(roomNumber);
        if (roomNumberFilter != null) {
            predicate = predicate.and(roomNumberFilter);
        }

        BooleanExpression hotelIdFilter = byHotelId(hotelId);
        if (hotelIdFilter != null) {
            predicate = predicate.and(hotelIdFilter);
        }

        BooleanExpression roomTypeFilter = byRoomType(roomType);
        if (roomTypeFilter != null) {
            predicate = predicate.and(roomTypeFilter);
        }

        return predicate;
    }
}
