package com.ntconsult.hotelreservation.infrastructure.api;

import com.ntconsult.hotelreservation.domain.model.Room;
import com.ntconsult.hotelreservation.domain.repository.RoomRepository;
import com.ntconsult.hotelreservation.domain.service.RoomService;
import com.ntconsult.hotelreservation.infrastructure.dto.CommonResult;
import com.ntconsult.hotelreservation.infrastructure.dto.input.RoomInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.RoomOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/rooms")
public class RoomResource extends GenericResource<Room, Long, RoomInputDTO, RoomOutputDTO,
        RoomRepository> {

    protected RoomResource(RoomService service) {
        super(service);
    }

    @Override
    @Operation(hidden = true)
    public CommonResult<RoomOutputDTO> create(RoomInputDTO dto) {
        return super.create(dto);
    }

    @Override
    @Operation(hidden = true)
    public CommonResult<RoomOutputDTO> update(RoomInputDTO dto, Long id) {
        return super.update(dto, id);
    }

    @Override
    @Operation(hidden = true)
    public CommonResult<Void> deleteById(Long id) {
        return super.deleteById(id);
    }

    @Override
    @Operation(hidden = true)
    public CommonResult<RoomOutputDTO> findById(Long id) {
        return super.findById(id);
    }
}
