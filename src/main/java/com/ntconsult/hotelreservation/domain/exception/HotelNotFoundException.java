package com.ntconsult.hotelreservation.domain.exception;

public class HotelNotFoundException extends RuntimeException {
    public HotelNotFoundException(Long id) {
        super("Hotel not found with id: " + id);
    }
}