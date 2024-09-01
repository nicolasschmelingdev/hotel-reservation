package com.ntconsult.hotelreservation.util;

import com.ntconsult.hotelreservation.domain.model.QHotel;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.stereotype.Component;

@Component
public class HotelSpecifications {

    public BooleanExpression byName(String name) {
        if (name == null || name.isEmpty()) {
            return null;
        }
        return QHotel.hotel.name.containsIgnoreCase(name);
    }

    public BooleanExpression byCity(String city) {
        if (city == null || city.isEmpty()) {
            return null;
        }
        return QHotel.hotel.city.eq(city);
    }

    public BooleanExpression byCountry(String country) {
        if (country == null || country.isEmpty()) {
            return null;
        }
        return QHotel.hotel.country.eq(country);
    }

    public BooleanExpression combineFilters(String name, String city, String country) {
        BooleanExpression predicate = QHotel.hotel.isNotNull();

        BooleanExpression nameFilter = byName(name);
        if (nameFilter != null) {
            predicate = predicate.and(nameFilter);
        }

        BooleanExpression cityFilter = byCity(city);
        if (cityFilter != null) {
            predicate = predicate.and(cityFilter);
        }

        BooleanExpression countryFilter = byCountry(country);
        if (countryFilter != null) {
            predicate = predicate.and(countryFilter);
        }

        return predicate;
    }
}
