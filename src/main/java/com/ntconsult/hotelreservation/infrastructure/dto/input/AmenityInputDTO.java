package com.ntconsult.hotelreservation.infrastructure.dto.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ntconsult.hotelreservation.infrastructure.dto.GenericDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class AmenityInputDTO implements Serializable, GenericDto {
    @JsonIgnore
    private Long id;
    private String name;
    private IdInputDTO hotel;

}
