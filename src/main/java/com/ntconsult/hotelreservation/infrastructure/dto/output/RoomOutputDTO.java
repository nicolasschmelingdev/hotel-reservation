package com.ntconsult.hotelreservation.infrastructure.dto.output;

import com.ntconsult.hotelreservation.domain.model.enums.RoomType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomOutputDTO {
    private Long id;
    private String roomNumber;
    private RoomType roomType;
    private Double price;
}
