package com.ntconsult.hotelreservation.infrastructure.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerDTO {
    private Long id;
    private String name;
    private String email;
    private String phone;
    // Getters and Setters
}
