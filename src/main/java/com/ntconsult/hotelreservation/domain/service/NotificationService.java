package com.ntconsult.hotelreservation.domain.service;

import com.ntconsult.hotelreservation.domain.model.Notification;
import com.ntconsult.hotelreservation.domain.repository.NotificationRepository;
import com.ntconsult.hotelreservation.infrastructure.dto.input.NotificationInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.NotificationOutputDTO;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class NotificationService extends GenericService<Notification, Long, NotificationInputDTO, NotificationOutputDTO,
        NotificationRepository> {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    protected NotificationService(NotificationRepository genericRepository, KafkaTemplate<String, Object> kafkaTemplate) {
        super(genericRepository);
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    protected void afterInsert(Notification notification) {
        kafkaTemplate.send("reservation-events", notification);
        super.afterInsert(notification);
    }

}