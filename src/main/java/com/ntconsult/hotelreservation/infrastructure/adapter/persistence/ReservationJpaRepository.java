package com.ntconsult.hotelreservation.infrastructure.adapter.persistence;

import com.ntconsult.hotelreservation.domain.model.Reservation;

import java.util.List;

public interface ReservationJpaRepository extends GenericJpaRepository<Reservation, Long> {
    List<Reservation> findReservationsByCustomCriteria(Long customerId, Long roomId, String status);
}
