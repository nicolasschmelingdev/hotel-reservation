package com.ntconsult.hotelreservation.infrastructure.dto.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReviewOutputDTO {
    private Long id;
    private Double rating;
    private String comment;
    private CustomerOutputDTO customer;
}
