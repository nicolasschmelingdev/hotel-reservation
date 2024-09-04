package com.ntconsult.hotelreservation.infrastructure.adapter.persistence;

import com.ntconsult.hotelreservation.domain.model.Hotel;
import com.ntconsult.hotelreservation.domain.model.QHotel;
import com.ntconsult.hotelreservation.domain.model.QReservation;
import com.ntconsult.hotelreservation.domain.model.QRoom;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class HotelJpaRepositoryImpl implements HotelJpaRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Hotel> findHotelsWithAvailableRooms(String city, LocalDate checkInDate, LocalDate checkOutDate, int minRoomsAvailable) {
        QHotel hotel = QHotel.hotel;
        QRoom room = QRoom.room;
        QReservation reservation = QReservation.reservation;

        return new JPAQueryFactory(entityManager)
                .selectFrom(hotel)
                .join(hotel.rooms, room)
                .where(hotel.city.eq(city)
                        .and(room.isAvailable.eq(true))
                        .and(room.id.notIn(
                                new JPAQueryFactory(entityManager)
                                        .select(reservation.room.id)
                                        .from(reservation)
                                        .where(reservation.checkInDate.before(checkOutDate)
                                                .and(reservation.checkOutDate.after(checkInDate)))
                        )))
                .groupBy(hotel.id)
                .having(room.count().goe(minRoomsAvailable))
                .fetch();
    }
}