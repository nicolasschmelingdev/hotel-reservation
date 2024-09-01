package com.ntconsult.hotelreservation.domain.model;

public enum ReservationStatus {
    PENDING(1),
    CONFIRMED(2),
    CANCELLED(3);

    private final int value;

    ReservationStatus(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ReservationStatus fromValue(int value) {
        for (ReservationStatus status : ReservationStatus.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
