package com.ntconsult.hotelreservation.infrastructure.adapter.messaging;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @KafkaListener(topics = "reservation-events", groupId = "hotel-group")
    public void consumeReservationNotification(Object message) {
        // Process the message (e.g., log it, handle the event)
        System.out.println("Received Message: " + message.toString());
    }
}