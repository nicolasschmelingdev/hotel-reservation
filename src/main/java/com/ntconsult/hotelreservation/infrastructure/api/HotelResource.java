package com.ntconsult.hotelreservation.infrastructure.api;

import com.ntconsult.hotelreservation.domain.model.Hotel;
import com.ntconsult.hotelreservation.domain.repository.HotelRepository;
import com.ntconsult.hotelreservation.domain.service.HotelService;
import com.ntconsult.hotelreservation.infrastructure.dto.CommonResult;
import com.ntconsult.hotelreservation.infrastructure.dto.PageFilters;
import com.ntconsult.hotelreservation.infrastructure.dto.PageResult;
import com.ntconsult.hotelreservation.infrastructure.dto.input.HotelInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.HotelOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.persistence.criteria.JoinType;
import jdk.jfr.Description;
import net.kaczmarzyk.spring.data.jpa.domain.Between;
import net.kaczmarzyk.spring.data.jpa.domain.GreaterThanOrEqual;
import net.kaczmarzyk.spring.data.jpa.domain.LikeIgnoreCase;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Conjunction;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Join;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Or;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/hotels")
public class HotelResource extends GenericResource<Hotel, Long, HotelInputDTO, HotelOutputDTO, HotelRepository> {

    private final HotelService hotelService;

    protected HotelResource(HotelService service) {
        super(service);
        this.hotelService = service;
    }

    @Override
    @Operation(summary = "Comparação de hotéis")
    @Description("Opção de comparar hotéis por preço, localização, comodidades e avaliações de outros usuários")
    @GetMapping
    public CommonResult<PageResult<HotelOutputDTO>> listRecords(
            @Join(path = "amenities", alias = "am")
            @Join(path = "reviews", alias = "re", type = JoinType.LEFT)
            @Join(path = "rooms", alias = "ro")
            @Join(path = "re.customer", alias = "cr")
            @Conjunction({
                    @Or(@Spec(path = "address", params = "address", spec = LikeIgnoreCase.class)),
                    @Or(@Spec(path = "city", params = "city", spec = LikeIgnoreCase.class)),
                    @Or(@Spec(path = "country", params = "country", spec = LikeIgnoreCase.class)),
                    @Or(@Spec(path = "averageRating", params = "averageRating", spec = GreaterThanOrEqual.class)),
                    @Or(@Spec(path = "ro.price", params = {"roomPrinceMin", "roomPriceMax"}, spec =
                            Between.class)),
                    @Or(@Spec(path = "re.rating", params = "reviewRating", spec = GreaterThanOrEqual.class)),
                    @Or(@Spec(path = "cr.name", params = "customerReview", spec = LikeIgnoreCase.class)),
                    @Or(@Spec(path = "am.name", params = "amenitiesName", spec = LikeIgnoreCase.class))
            }) Specification<Hotel> espec, PageFilters pageFilters) {
        return super.listRecords(espec, pageFilters);
    }

    @Operation(summary = "Pesquisa de hotéis com base em destino e datas")
    @GetMapping("/search")
    public CommonResult<List<HotelOutputDTO>> searchHotels(
            @RequestParam String city,
            @RequestParam LocalDate checkInDate,
            @RequestParam LocalDate checkOutDate,
            @RequestParam Integer numberOfRooms,
            @RequestParam Integer numberOfGuests) {

        List<HotelOutputDTO> hotels = hotelService.searchHotels(city, checkInDate, checkOutDate, numberOfRooms, numberOfGuests);
        return CommonResult.success(hotels);
    }

    @Override
    @Operation(hidden = true)
    public CommonResult<Void> deleteById(Long id) {
        return super.deleteById(id);
    }

    @Override
    @Operation(hidden = true)
    public CommonResult<HotelOutputDTO> update(HotelInputDTO dto, Long id) {
        return super.update(dto, id);
    }

    @Override
    @Operation(hidden = true)
    public CommonResult<HotelOutputDTO> create(HotelInputDTO dto) {
        return super.create(dto);
    }
}
