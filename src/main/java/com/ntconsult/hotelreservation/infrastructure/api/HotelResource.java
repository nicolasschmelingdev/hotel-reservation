package com.ntconsult.hotelreservation.infrastructure.api;

import com.ntconsult.hotelreservation.domain.model.Hotel;
import com.ntconsult.hotelreservation.domain.repository.HotelRepository;
import com.ntconsult.hotelreservation.domain.service.HotelService;
import com.ntconsult.hotelreservation.infrastructure.dto.input.HotelInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.HotelOutputDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/hotels")
public class HotelResource extends GenericResource<Hotel, Long, HotelInputDTO, HotelOutputDTO,
        HotelRepository> {

    protected HotelResource(HotelService service) {
        super(service);
    }
}
