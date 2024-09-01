package com.ntconsult.hotelreservation.infrastructure.api.controller;

import com.ntconsult.hotelreservation.domain.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationMapper reservationMapper;

    @GetMapping
    public ResponseEntity<List<ReservationDTO>> searchReservations(
            @RequestParam(required = false) Long customerId,
            @RequestParam(required = false) Long roomId,
            @RequestParam(required = false) String status) {
        return ResponseEntity.ok(reservationService.searchReservations(customerId, roomId, status)
                .stream()
                .map(reservationMapper::toDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        return ResponseEntity.ok(reservationMapper.toDTO(reservationService.getReservationById(id)));
    }

    @PostMapping
    public ResponseEntity<ReservationDTO> createReservation(@RequestBody ReservationDTO reservationDTO) {
        return ResponseEntity.ok(reservationMapper.toDTO(reservationService.createReservation(reservationMapper.toEntity(reservationDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationDTO> updateReservation(@PathVariable Long id, @RequestBody ReservationDTO reservationDTO) {
        return ResponseEntity.ok(reservationMapper.toDTO(reservationService.updateReservation(id, reservationMapper.toEntity(reservationDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
