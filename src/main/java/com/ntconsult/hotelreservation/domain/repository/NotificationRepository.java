package com.ntconsult.hotelreservation.domain.repository;

import com.ntconsult.hotelreservation.domain.model.Notification;
import com.ntconsult.hotelreservation.infrastructure.adapter.persistence.NotificationJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends GenericRepository<Notification, Long>, NotificationJpaRepository {
}