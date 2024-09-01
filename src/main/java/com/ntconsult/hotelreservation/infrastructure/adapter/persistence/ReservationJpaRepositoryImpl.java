package com.ntconsult.hotelreservation.infrastructure.adapter.persistence;

import com.ntconsult.hotelreservation.domain.model.QReservation;
import com.ntconsult.hotelreservation.domain.model.Reservation;
import com.ntconsult.hotelreservation.domain.model.ReservationStatus;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReservationJpaRepositoryImpl implements ReservationJpaRepository {

    private final EntityManager entityManager;

    public ReservationJpaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Reservation> findReservationsByCustomCriteria(Long customerId, Long roomId, String status) {
        QReservation reservation = QReservation.reservation;
        BooleanExpression predicate = reservation.isNotNull();

        if (customerId != null) {
            predicate = predicate.and(reservation.customer.id.eq(customerId));
        }

        if (roomId != null) {
            predicate = predicate.and(reservation.room.id.eq(roomId));
        }

        if (status != null && !status.isEmpty()) {
            predicate = predicate.and(reservation.status.eq(ReservationStatus.valueOf(status.toUpperCase())));
        }

        return new JPAQueryFactory(this.entityManager)
                .selectFrom(reservation)
                .where(predicate)
                .fetch();
    }
}