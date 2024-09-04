package com.ntconsult.hotelreservation.domain.service;

import com.ntconsult.hotelreservation.domain.model.Hotel;
import com.ntconsult.hotelreservation.domain.repository.HotelRepository;
import com.ntconsult.hotelreservation.infrastructure.dto.input.HotelInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.HotelOutputDTO;
import com.ntconsult.hotelreservation.util.ObjectMapperUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HotelService extends GenericService<Hotel, Long, HotelInputDTO, HotelOutputDTO, HotelRepository> {

    public HotelService(HotelRepository hotelRepository) {
        super(hotelRepository);
    }

    public List<HotelOutputDTO> searchHotels(String city, LocalDate checkInDate, LocalDate checkOutDate, Integer numberOfRooms, Integer numberOfGuests) {
        List<Hotel> hotels = this.getRepository().findHotelsWithAvailableRooms(city, checkInDate, checkOutDate, numberOfRooms);

        return hotels.stream()
                .filter(hotel -> hotel.getMaxGuests() >= numberOfGuests)
                .map(hotel -> ObjectMapperUtils.map(hotel, HotelOutputDTO.class))
                .collect(Collectors.toList());
    }

}
