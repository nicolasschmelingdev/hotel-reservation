package com.ntconsult.hotelreservation.domain.repository;

import com.ntconsult.hotelreservation.domain.model.Hotel;
import com.ntconsult.hotelreservation.infrastructure.adapter.persistence.HotelJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotelRepository extends GenericRepository<Hotel, Long>, HotelJpaRepository {
}