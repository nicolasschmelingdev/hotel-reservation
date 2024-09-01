package com.ntconsult.hotelreservation.domain.service;

import com.ntconsult.hotelreservation.domain.model.Room;
import com.ntconsult.hotelreservation.domain.repository.RoomRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Transactional(readOnly = true)
    public List<Room> searchRooms(String roomNumber, Long hotelId, String roomType) {
        return roomRepository.findRoomsByCustomCriteria(roomNumber, hotelId, roomType);
    }

    @Transactional(readOnly = true)
    public Room getRoomById(Long id) {
        return roomRepository.findById(id).orElseThrow(() -> new RoomNotFoundException(id));
    }

    @Transactional
    public Room createRoom(Room room) {
        return roomRepository.save(room);
    }

    @Transactional
    public Room updateRoom(Long id, Room roomDetails) {
        Room room = getRoomById(id);
        room.setRoomNumber(roomDetails.getRoomNumber());
        room.setRoomType(roomDetails.getRoomType());
        room.setPrice(roomDetails.getPrice());
        return roomRepository.save(room);
    }

    @Transactional
    public void deleteRoom(Long id) {
        Room room = getRoomById(id);
        roomRepository.delete(room);
    }
}