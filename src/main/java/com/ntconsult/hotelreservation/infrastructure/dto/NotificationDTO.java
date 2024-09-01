package com.ntconsult.hotelreservation.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class NotificationDTO {
    private Long id;
    private Long reservationId;
    private String message;
    private LocalDate sentDate;
    // Getters and Setters
}
