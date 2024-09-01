package com.ntconsult.hotelreservation.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class ReservationDTO {
    private Long id;
    private Long customerId;
    private Long roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String status;
}
