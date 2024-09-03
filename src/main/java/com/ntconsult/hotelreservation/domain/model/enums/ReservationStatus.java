package com.ntconsult.hotelreservation.domain.model.enums;

import lombok.Getter;

@Getter
public enum ReservationStatus {
    PENDING(1),
    CONFIRMED(2),
    CANCELLED(3);

    private final int value;

    ReservationStatus(int value) {
        this.value = value;
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
