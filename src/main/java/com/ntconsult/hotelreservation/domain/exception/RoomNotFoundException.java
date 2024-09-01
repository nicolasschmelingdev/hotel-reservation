package com.ntconsult.hotelreservation.domain.exception;

public class RoomNotFoundException extends RuntimeException {
    public RoomNotFoundException(Long id) {
        super("Room not found with id: " + id);
    }
}