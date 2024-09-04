package com.ntconsult.hotelreservation.infrastructure.dto.input;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ntconsult.hotelreservation.infrastructure.dto.GenericDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CustomerInputDTO implements Serializable, GenericDto {
    @JsonIgnore
    private Long id;
    private String name;
    private String email;
    private String phone;
}
