package com.ntconsult.hotelreservation.infrastructure.api.controller;

import com.ntconsult.hotelreservation.domain.service.RoomService;
import com.ntconsult.hotelreservation.infrastructure.dto.RoomDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomMapper roomMapper;

    @GetMapping
    public ResponseEntity<List<RoomDTO>> searchRooms(
            @RequestParam(required = false) String roomNumber,
            @RequestParam(required = false) Long hotelId,
            @RequestParam(required = false) String roomType) {
        return ResponseEntity.ok(roomService.searchRooms(roomNumber, hotelId, roomType)
                .stream()
                .map(roomMapper::toDTO)
                .toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDTO> getRoomById(@PathVariable Long id) {
        return ResponseEntity.ok(roomMapper.toDTO(roomService.getRoomById(id)));
    }

    @PostMapping
    public ResponseEntity<RoomDTO> createRoom(@RequestBody RoomDTO roomDTO) {
        return ResponseEntity.ok(roomMapper.toDTO(roomService.createRoom(roomMapper.toEntity(roomDTO))));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomDTO> updateRoom(@PathVariable Long id, @RequestBody RoomDTO roomDTO) {
        return ResponseEntity.ok(roomMapper.toDTO(roomService.updateRoom(id, roomMapper.toEntity(roomDTO))));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
