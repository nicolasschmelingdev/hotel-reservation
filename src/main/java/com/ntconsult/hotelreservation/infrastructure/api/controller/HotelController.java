package com.ntconsult.hotelreservation.infrastructure.api.controller;

import com.ntconsult.hotelreservation.domain.service.HotelService;
import com.ntconsult.hotelreservation.infrastructure.dto.HotelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @Autowired
    private HotelMapper hotelMapper;

    @GetMapping
    public ResponseEntity<List<HotelDTO>> searchHotels(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) String country) {
        return ResponseEntity.ok(hotelService.searchHotels(name, city, country)
                .stream()
                .map(hotelMapper::toDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<HotelDTO> getHotelById(@PathVariable Long id) {
        return ResponseEntity.ok(hotelMapper.toDTO(hotelService.getHotelById(id)));
    }

    @PostMapping
    public ResponseEntity<HotelDTO> createHotel(@RequestBody HotelDTO hotelDTO) {
        return ResponseEntity.ok(hotelMapper.toDTO(hotelService.createHotel(hotelMapper.toEntity(hotelDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HotelDTO> updateHotel(@PathVariable Long id, @RequestBody HotelDTO hotelDTO) {
        return ResponseEntity.ok(hotelMapper.toDTO(hotelService.updateHotel(id, hotelMapper.toEntity(hotelDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteHotel(@PathVariable Long id) {
        hotelService.deleteHotel(id);
        return ResponseEntity.noContent().build();
    }
}
