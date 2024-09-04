package com.ntconsult.hotelreservation.domain.service;

import com.ntconsult.hotelreservation.domain.model.Hotel;
import com.ntconsult.hotelreservation.domain.model.Review;
import com.ntconsult.hotelreservation.domain.repository.ReviewRepository;
import com.ntconsult.hotelreservation.infrastructure.dto.input.ReviewInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.ReviewOutputDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService extends GenericService<Review, Long, ReviewInputDTO, ReviewOutputDTO,
        ReviewRepository> {

    private final HotelService hotelService;

    protected ReviewService(ReviewRepository genericRepository, HotelService hotelService) {
        super(genericRepository);
        this.hotelService = hotelService;
    }

    @Override
    protected void afterInsert(Review review) {
        updateHotelAverageRating(review.getHotel());
        super.afterInsert(review);
    }

    @Transactional
    protected void updateHotelAverageRating(Hotel hotel) {
        hotel = this.hotelService.findByIdEntity(hotel.getId());
        double averageRating = hotel.getReviews().stream()
                .mapToDouble(Review::getRating)
                .average()
                .orElse(0.0);

        hotel.setAverageRating(averageRating);
        this.hotelService.createByEntity(hotel);
    }
}
