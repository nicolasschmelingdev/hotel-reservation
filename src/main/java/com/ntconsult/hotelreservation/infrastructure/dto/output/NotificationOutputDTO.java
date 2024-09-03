package com.ntconsult.hotelreservation.infrastructure.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationOutputDTO {
    private Long id;
    private ReservationOutputDTO reservation;
    private String message;
    private LocalDate sentDate;
}
