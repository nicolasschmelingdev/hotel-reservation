package com.ntconsult.hotelreservation.domain.repository;

import com.ntconsult.hotelreservation.domain.model.Review;
import com.ntconsult.hotelreservation.infrastructure.adapter.persistence.ReviewJpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends GenericRepository<Review, Long>, ReviewJpaRepository {
}