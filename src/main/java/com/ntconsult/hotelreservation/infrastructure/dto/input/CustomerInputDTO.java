package com.ntconsult.hotelreservation.infrastructure.dto.input;

import com.ntconsult.hotelreservation.infrastructure.dto.GenericDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CustomerInputDTO implements Serializable, GenericDto {
    private Long id;
    private String name;
    private String email;
    private String phone;
}
