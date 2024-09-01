package com.ntconsult.hotelreservation.domain.service;

import com.ntconsult.hotelreservation.domain.exception.HotelNotFoundException;
import com.ntconsult.hotelreservation.domain.model.Hotel;
import com.ntconsult.hotelreservation.domain.repository.HotelRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HotelService {

    private final HotelRepository hotelRepository;

    public HotelService(HotelRepository hotelRepository) {
        this.hotelRepository = hotelRepository;
    }

    @Transactional(readOnly = true)
    public List<Hotel> searchHotels(String name, String city, String country) {
        return hotelRepository.findHotelsByCustomCriteria(name, city, country);
    }

    @Transactional(readOnly = true)
    public Hotel getHotelById(Long id) {
        return hotelRepository.findById(id).orElseThrow(() -> new HotelNotFoundException(id));
    }

    @Transactional
    public Hotel createHotel(Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    @Transactional
    public Hotel updateHotel(Long id, Hotel hotelDetails) {
        Hotel hotel = getHotelById(id);
        hotel.setName(hotelDetails.getName());
        hotel.setAddress(hotelDetails.getAddress());
        hotel.setCity(hotelDetails.getCity());
        hotel.setCountry(hotelDetails.getCountry());
        return hotelRepository.save(hotel);
    }

    @Transactional
    public void deleteHotel(Long id) {
        Hotel hotel = getHotelById(id);
        hotelRepository.delete(hotel);
    }
}