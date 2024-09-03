package com.ntconsult.hotelreservation.domain.service;

import com.ntconsult.hotelreservation.domain.model.Room;
import com.ntconsult.hotelreservation.domain.repository.RoomRepository;
import com.ntconsult.hotelreservation.infrastructure.dto.input.RoomInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.RoomOutputDTO;
import org.springframework.stereotype.Service;

@Service
public class RoomService extends GenericService<Room, Long, RoomInputDTO, RoomOutputDTO,
        RoomRepository> {


    protected RoomService(RoomRepository genericRepository) {
        super(genericRepository);
    }

}