package com.ntconsult.hotelreservation.infrastructure.adapter.persistence;

import com.ntconsult.hotelreservation.domain.model.Room;

import java.util.List;

public interface RoomJpaRepository {
    List<Room> findRoomsByCustomCriteria(String roomNumber, Long hotelId, String roomType);
}
