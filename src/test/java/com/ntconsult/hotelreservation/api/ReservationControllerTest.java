package com.ntconsult.hotelreservation.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.ntconsult.hotelreservation.domain.model.enums.ReservationStatus;
import com.ntconsult.hotelreservation.domain.service.ReservationService;
import com.ntconsult.hotelreservation.infrastructure.dto.input.IdInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.input.ReservationInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.ReservationOutputDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ReservationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReservationService reservationService;

    @Test
    void createReservation_ShouldReturnCreated_WhenValidInput() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());

        ReservationInputDTO inputDTO = ReservationInputDTO.builder()
                .room(IdInputDTO.builder()
                        .id(1L)
                        .build())
                .customer(IdInputDTO.builder()
                        .id(1L)
                        .build())
                .checkInDate(LocalDate.now())
                .checkOutDate(LocalDate.now().plusDays(5))
                .build();

        ReservationOutputDTO outputDTO = ReservationOutputDTO.builder()
                .id(1L)
                .status(ReservationStatus.CONFIRMED)
                .build();

        Mockito.when(reservationService.create(Mockito.any())).thenReturn(outputDTO);

        mockMvc.perform(post("/api/reservations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(inputDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.data.status").value(ReservationStatus.CONFIRMED.toString()));
    }
}
