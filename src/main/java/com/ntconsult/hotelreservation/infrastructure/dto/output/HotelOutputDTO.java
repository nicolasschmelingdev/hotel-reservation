package com.ntconsult.hotelreservation.infrastructure.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelOutputDTO implements Serializable {
    private Long id;
    private String name;
    private String address;
    private String city;
    private String country;
    private Double averageRating;
    private Integer maxGuests;
    private List<RoomOutputDTO> rooms;
    private List<ReviewOutputDTO> reviews;
    private List<AmenityOutputDTO> amenities;
}
