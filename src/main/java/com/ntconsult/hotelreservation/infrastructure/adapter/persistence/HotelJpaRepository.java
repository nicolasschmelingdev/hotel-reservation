package com.ntconsult.hotelreservation.infrastructure.adapter.persistence;

import com.ntconsult.hotelreservation.domain.model.Hotel;

import java.time.LocalDate;
import java.util.List;

public interface HotelJpaRepository extends GenericJpaRepository<Hotel, Long> {
    List<Hotel> findHotelsWithAvailableRooms(String city, LocalDate checkInDate, LocalDate checkOutDate, int minRoomsAvailable);
}
