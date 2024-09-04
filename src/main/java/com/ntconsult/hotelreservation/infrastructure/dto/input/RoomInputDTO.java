package com.ntconsult.hotelreservation.infrastructure.dto.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ntconsult.hotelreservation.domain.model.enums.RoomType;
import com.ntconsult.hotelreservation.infrastructure.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomInputDTO implements Serializable, GenericDto {
    @JsonIgnore
    private Long id;
    private String roomNumber;
    private RoomType roomType;
    private Double price;
    private IdInputDTO hotel;
}
