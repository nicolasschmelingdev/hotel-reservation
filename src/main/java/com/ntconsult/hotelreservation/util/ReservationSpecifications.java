package com.ntconsult.hotelreservation.util;

import com.ntconsult.hotelreservation.domain.model.QReservation;
import com.ntconsult.hotelreservation.domain.model.Reservation;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Component;

@Component
public class ReservationSpecifications {

    public BooleanExpression byCustomerId(Long customerId) {
        if (customerId == null) {
            return null;
        }
        return QReservation.reservation.customer.id.eq(customerId);
    }

    public BooleanExpression byRoomId(Long roomId) {
        if (roomId == null) {
            return null;
        }
        return QReservation.reservation.room.id.eq(roomId);
    }

    public BooleanExpression byStatus(String status) {
        if (status == null || status.isEmpty()) {
            return null;
        }
        return QReservation.reservation.status.eq(Reservation.ReservationStatus.valueOf(status.toUpperCase()));
    }

    public BooleanExpression combineFilters(Long customerId, Long roomId, String status) {
        BooleanExpression predicate = QReservation.reservation.isNotNull();

        BooleanExpression customerIdFilter = byCustomerId(customerId);
        if (customerIdFilter != null) {
            predicate = predicate.and(customerIdFilter);
        }

        BooleanExpression roomIdFilter = byRoomId(roomId);
        if (roomIdFilter != null) {
            predicate = predicate.and(roomIdFilter);
        }

        BooleanExpression statusFilter = byStatus(status);
        if (statusFilter != null) {
            predicate = predicate.and(statusFilter);
        }

        return predicate;
    }
}
