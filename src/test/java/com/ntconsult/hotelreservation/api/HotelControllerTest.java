package com.ntconsult.hotelreservation.api;

import com.ntconsult.hotelreservation.domain.service.HotelService;
import com.ntconsult.hotelreservation.infrastructure.dto.output.HotelOutputDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class HotelControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private HotelService hotelService;

    @Test
    void searchHotels_ShouldReturnListOfHotels_WhenValidInput() throws Exception {
        HotelOutputDTO hotel1 = HotelOutputDTO.builder()
                .id(1L)
                .name("Hotel 1")
                .city("Los Angeles")
                .address("Address 1")
                .averageRating(4.5)
                .build();

        HotelOutputDTO hotel2 = HotelOutputDTO.builder()
                .id(2L)
                .name("Hotel 2")
                .city("Los Angeles")
                .address("Address 2")
                .averageRating(4.0)
                .build();

        List<HotelOutputDTO> mockHotels = List.of(hotel1, hotel2);

        Mockito.when(hotelService.searchHotels(
                Mockito.anyString(),
                Mockito.any(LocalDate.class),
                Mockito.any(LocalDate.class),
                Mockito.anyInt(),
                Mockito.anyInt()
        )).thenReturn(mockHotels);

        mockMvc.perform(get("/api/hotels/search")
                        .param("city", "Los Angeles")
                        .param("checkInDate", "2024-09-15")
                        .param("checkOutDate", "2024-09-20")
                        .param("numberOfRooms", "2")
                        .param("numberOfGuests", "4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.data").isArray())
                .andExpect(jsonPath("$.data.length()").value(2))
                .andExpect(jsonPath("$.data[0].name").value("Hotel 1"));
    }
}
