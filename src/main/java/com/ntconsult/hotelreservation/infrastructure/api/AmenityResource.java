package com.ntconsult.hotelreservation.infrastructure.api;

import com.ntconsult.hotelreservation.domain.model.Amenity;
import com.ntconsult.hotelreservation.domain.repository.AmenityRepository;
import com.ntconsult.hotelreservation.domain.service.AmenityService;
import com.ntconsult.hotelreservation.infrastructure.dto.input.AmenityInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.AmenityOutputDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/amenities")
public class AmenityResource extends GenericResource<Amenity, Long, AmenityInputDTO, AmenityOutputDTO,
        AmenityRepository> {

    protected AmenityResource(AmenityService service) {
        super(service);
    }

}
