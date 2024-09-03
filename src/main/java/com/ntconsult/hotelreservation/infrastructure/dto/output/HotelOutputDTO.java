package com.ntconsult.hotelreservation.infrastructure.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelOutputDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String country;
}
