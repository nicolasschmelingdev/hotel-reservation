package com.ntconsult.hotelreservation.infrastructure.dto.input;

import com.ntconsult.hotelreservation.domain.model.enums.ReservationStatus;
import com.ntconsult.hotelreservation.infrastructure.dto.GenericDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
public class ReservationInputDTO implements Serializable, GenericDto {
    private Long id;
    private IdInputDTO customer;
    private IdInputDTO room;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private ReservationStatus status;
}
