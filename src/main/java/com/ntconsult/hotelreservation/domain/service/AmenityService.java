package com.ntconsult.hotelreservation.domain.service;

import com.ntconsult.hotelreservation.domain.model.Amenity;
import com.ntconsult.hotelreservation.domain.repository.AmenityRepository;
import com.ntconsult.hotelreservation.infrastructure.dto.input.AmenityInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.AmenityOutputDTO;
import org.springframework.stereotype.Service;

@Service
public class AmenityService extends GenericService<Amenity, Long, AmenityInputDTO, AmenityOutputDTO,
        AmenityRepository> {

    protected AmenityService(AmenityRepository genericRepository) {
        super(genericRepository);
    }
}
