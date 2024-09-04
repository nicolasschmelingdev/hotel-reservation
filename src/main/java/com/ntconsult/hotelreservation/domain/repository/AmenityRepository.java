package com.ntconsult.hotelreservation.domain.repository;

import com.ntconsult.hotelreservation.domain.model.Amenity;
import com.ntconsult.hotelreservation.infrastructure.adapter.persistence.AmenityJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AmenityRepository extends GenericRepository<Amenity, Long>, AmenityJpaRepository {
}