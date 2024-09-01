import com.ntconsult.hotelreservation.domain.model.Notification;
import com.ntconsult.hotelreservation.domain.model.Reservation;
import com.ntconsult.hotelreservation.domain.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Transactional
    public void sendNotification(Reservation reservation, String message) {
        Notification notification = new Notification();
        notification.setReservation(reservation);
        notification.setMessage(message);
        notification.setSentDate(LocalDate.now());

        notificationRepository.save(notification);
        kafkaTemplate.send("reservation-events", notification);
    }
}