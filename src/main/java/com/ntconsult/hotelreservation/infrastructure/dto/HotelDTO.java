package com.ntconsult.hotelreservation.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HotelDTO {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String country;
}
