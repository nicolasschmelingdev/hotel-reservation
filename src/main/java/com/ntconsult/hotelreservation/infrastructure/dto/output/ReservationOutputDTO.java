package com.ntconsult.hotelreservation.infrastructure.dto.output;

import com.ntconsult.hotelreservation.domain.model.enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationOutputDTO {
    private Long id;
    private CustomerOutputDTO customer;
    private RoomOutputDTO roomId;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private ReservationStatus status;
}
