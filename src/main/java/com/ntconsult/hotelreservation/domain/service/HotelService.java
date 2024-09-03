package com.ntconsult.hotelreservation.domain.service;

import com.ntconsult.hotelreservation.domain.model.Hotel;
import com.ntconsult.hotelreservation.domain.repository.HotelRepository;
import com.ntconsult.hotelreservation.infrastructure.dto.input.HotelInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.HotelOutputDTO;
import org.springframework.stereotype.Service;

@Service
public class HotelService extends GenericService<Hotel, Long, HotelInputDTO, HotelOutputDTO,
        HotelRepository> {

    protected HotelService(HotelRepository genericRepository) {
        super(genericRepository);
    }

    @Override
    protected void beforeUpdate(Hotel registro, HotelInputDTO dto) {
        registro.setId(dto.getId());
        super.beforeUpdate(registro, dto);
    }
}