package com.ntconsult.hotelreservation.domain.repository;

import com.ntconsult.hotelreservation.domain.model.Room;
import com.ntconsult.hotelreservation.infrastructure.adapter.persistence.RoomJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends GenericRepository<Room, Long>, RoomJpaRepository {
}