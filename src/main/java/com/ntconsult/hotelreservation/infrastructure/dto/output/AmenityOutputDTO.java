package com.ntconsult.hotelreservation.infrastructure.dto.output;

import com.ntconsult.hotelreservation.infrastructure.dto.GenericDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AmenityOutputDTO implements Serializable, GenericDto {

    private Long id;
    private String name;

}
