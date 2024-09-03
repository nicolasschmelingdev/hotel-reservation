package com.ntconsult.hotelreservation.infrastructure.dto.input;

import com.ntconsult.hotelreservation.infrastructure.dto.GenericDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class HotelInputDTO implements Serializable, GenericDto {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String country;
}
