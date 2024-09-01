package com.ntconsult.hotelreservation.infrastructure.adapter.messaging;

import com.ntconsult.hotelreservation.domain.model.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducer {

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public void sendReservationNotification(Notification notification) {
        kafkaTemplate.send("reservation-events", notification);
    }
}