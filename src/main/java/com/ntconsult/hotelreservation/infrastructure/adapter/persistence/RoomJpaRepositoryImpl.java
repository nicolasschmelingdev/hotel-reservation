package com.ntconsult.hotelreservation.infrastructure.adapter.persistence;

import com.ntconsult.hotelreservation.domain.model.QRoom;
import com.ntconsult.hotelreservation.domain.model.Room;
import com.ntconsult.hotelreservation.domain.model.enums.RoomType;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoomJpaRepositoryImpl implements RoomJpaRepository {

    private final EntityManager entityManager;

    public RoomJpaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Room> findRoomsByCustomCriteria(String roomNumber, Long hotelId, String roomType) {
        QRoom room = QRoom.room;
        BooleanExpression predicate = room.isNotNull();

        if (roomNumber != null && !roomNumber.isEmpty()) {
            predicate = predicate.and(room.roomNumber.containsIgnoreCase(roomNumber));
        }

        if (hotelId != null) {
            predicate = predicate.and(room.hotel.id.eq(hotelId));
        }

        if (roomType != null && !roomType.isEmpty()) {
            predicate = predicate.and(room.roomType.eq(RoomType.valueOf(roomType.toUpperCase())));
        }

        return new JPAQueryFactory(this.entityManager)
                .selectFrom(room)
                .where(predicate)
                .fetch();
    }
}