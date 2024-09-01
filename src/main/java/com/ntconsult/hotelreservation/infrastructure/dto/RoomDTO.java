package com.ntconsult.hotelreservation.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoomDTO {
    private Long id;
    private String roomNumber;
    private String roomType;
    private Double price;
    private Long hotelId;
    // Getters and Setters
}
