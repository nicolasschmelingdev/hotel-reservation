package com.ntconsult.hotelreservation.infrastructure.adapter.persistence;

import com.ntconsult.hotelreservation.domain.model.Hotel;
import com.ntconsult.hotelreservation.domain.model.QHotel;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class HotelJpaRepositoryImpl implements HotelJpaRepository {

    private final EntityManager entityManager;

    public HotelJpaRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Hotel> findHotelsByCustomCriteria(String name, String city, String country) {
        QHotel hotel = QHotel.hotel;
        BooleanExpression predicate = hotel.isNotNull();

        if (name != null && !name.isEmpty()) {
            predicate = predicate.and(hotel.name.containsIgnoreCase(name));
        }

        if (city != null && !city.isEmpty()) {
            predicate = predicate.and(hotel.city.eq(city));
        }

        if (country != null && !country.isEmpty()) {
            predicate = predicate.and(hotel.country.eq(country));
        }

        return new JPAQueryFactory(this.entityManager)
                .selectFrom(hotel)
                .where(predicate)
                .fetch();
    }
}