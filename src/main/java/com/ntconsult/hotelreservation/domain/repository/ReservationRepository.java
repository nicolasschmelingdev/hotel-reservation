package com.ntconsult.hotelreservation.domain.repository;

import com.ntconsult.hotelreservation.domain.model.Reservation;
import com.ntconsult.hotelreservation.infrastructure.adapter.persistence.ReservationJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends GenericRepository<Reservation, Long>, ReservationJpaRepository {
}