package com.ntconsult.hotelreservation.domain.model;

public enum RoomType {
    SINGLE(1),
    DOUBLE(2),
    SUITE(3);

    private final int value;

    RoomType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static RoomType fromValue(int value) {
        for (RoomType type : RoomType.values()) {
            if (type.getValue() == value) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown value: " + value);
    }
}
