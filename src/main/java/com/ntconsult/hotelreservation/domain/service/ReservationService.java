package com.ntconsult.hotelreservation.domain.service;

import com.ntconsult.hotelreservation.domain.model.Reservation;
import com.ntconsult.hotelreservation.domain.model.enums.ReservationStatus;
import com.ntconsult.hotelreservation.domain.repository.ReservationRepository;
import com.ntconsult.hotelreservation.infrastructure.dto.input.IdInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.input.NotificationInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.input.ReservationInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.ReservationOutputDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReservationService extends GenericService<Reservation, Long, ReservationInputDTO, ReservationOutputDTO,
        ReservationRepository> {

    private final NotificationService notificationService;

    protected ReservationService(ReservationRepository genericRepository, NotificationService notificationService) {
        super(genericRepository);
        this.notificationService = notificationService;
    }

    @Override
    protected void afterInsert(Reservation registro) {
        this.notificationService.create(NotificationInputDTO.builder()
                .reservation(IdInputDTO.builder()
                        .id(registro.getId())
                        .build()
                )
                .sentDate(LocalDate.now())
                .message("Your reservation has been confirmed.")
                .build());

        super.afterInsert(registro);
    }

    @Override
    protected void afterUpdate(Reservation registro) {
        if (registro.getStatus().equals(ReservationStatus.CANCELLED)) {
            this.notificationService.create(NotificationInputDTO.builder()
                    .reservation(IdInputDTO.builder()
                            .id(registro.getId())
                            .build()
                    )
                    .sentDate(LocalDate.now())
                    .message("Your reservation has been canceled.")
                    .build());
        }
        super.afterUpdate(registro);
    }

}
