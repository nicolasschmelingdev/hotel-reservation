package com.ntconsult.hotelreservation.infrastructure.api;

import com.ntconsult.hotelreservation.domain.model.Reservation;
import com.ntconsult.hotelreservation.domain.repository.ReservationRepository;
import com.ntconsult.hotelreservation.domain.service.ReservationService;
import com.ntconsult.hotelreservation.infrastructure.dto.input.ReservationInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.ReservationOutputDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationResource extends GenericResource<Reservation, Long, ReservationInputDTO, ReservationOutputDTO,
        ReservationRepository> {

    protected ReservationResource(ReservationService service) {
        super(service);
    }
}
