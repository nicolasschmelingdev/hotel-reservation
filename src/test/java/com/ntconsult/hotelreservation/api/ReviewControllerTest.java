package com.ntconsult.hotelreservation.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ntconsult.hotelreservation.domain.service.ReviewService;
import com.ntconsult.hotelreservation.infrastructure.dto.input.IdInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.input.ReviewInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.ReviewOutputDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ReviewControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReviewService reviewService;

    @Test
    void createReview_ShouldReturnCreated_WhenValidInput() throws Exception {
        ReviewInputDTO inputDTO = ReviewInputDTO.builder()
                .hotel(IdInputDTO.builder()
                        .id(1L)
                        .build())
                .customer(IdInputDTO.builder()
                        .id(1L)
                        .build())
                .rating(10.0)
                .comment("Excellent hotel!")
                .build();

        ReviewOutputDTO outputDTO = ReviewOutputDTO.builder()
                .id(1L)
                .build();

        Mockito.when(reviewService.create(Mockito.any())).thenReturn(outputDTO);

        mockMvc.perform(post("/api/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(inputDTO)))
                .andExpect(status().isCreated());
    }
}
