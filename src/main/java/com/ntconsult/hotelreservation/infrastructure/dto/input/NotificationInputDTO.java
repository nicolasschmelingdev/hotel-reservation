package com.ntconsult.hotelreservation.infrastructure.dto.input;

import com.ntconsult.hotelreservation.infrastructure.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationInputDTO implements Serializable, GenericDto {
    private Long id;
    private IdInputDTO reservation;
    private String message;
    private LocalDate sentDate;
}
