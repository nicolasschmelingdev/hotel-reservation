package com.ntconsult.hotelreservation.infrastructure.api;

import com.ntconsult.hotelreservation.domain.model.Room;
import com.ntconsult.hotelreservation.domain.repository.RoomRepository;
import com.ntconsult.hotelreservation.domain.service.GenericService;
import com.ntconsult.hotelreservation.infrastructure.dto.input.RoomInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.RoomOutputDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")
public class RoomResource extends GenericResource<Room, Long, RoomInputDTO, RoomOutputDTO,
        RoomRepository> {

    protected RoomResource(GenericService<Room, Long, RoomInputDTO, RoomOutputDTO, RoomRepository> service) {
        super(service);
    }
}
