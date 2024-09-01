package com.ntconsult.hotelreservation.infrastructure.adapter.persistence;

import com.ntconsult.hotelreservation.domain.model.Hotel;

import java.util.List;

public interface HotelJpaRepository extends GenericJpaRepository<Hotel, Long> {
    List<Hotel> findHotelsByCustomCriteria(String name, String city, String country);
}
