package com.ntconsult.hotelreservation.infrastructure.api;

import com.ntconsult.hotelreservation.domain.model.Review;
import com.ntconsult.hotelreservation.domain.repository.ReviewRepository;
import com.ntconsult.hotelreservation.domain.service.ReviewService;
import com.ntconsult.hotelreservation.infrastructure.dto.CommonResult;
import com.ntconsult.hotelreservation.infrastructure.dto.ErrorDetailsDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.input.ReviewInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.ReviewOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reviews")
public class ReviewResource extends GenericResource<Review, Long, ReviewInputDTO, ReviewOutputDTO,
        ReviewRepository> {

    protected ReviewResource(ReviewService service) {
        super(service);
    }

    @Override
    @Operation(
            summary = "Realizar um avaliação de hotel",
            description = "Informar os dados do Payload corretamente para criar uma nova avaliação",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cadastro efetuado com sucesso", content = @Content(schema = @Schema(implementation = CommonResult.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição mal formada", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Recurso não autorizado", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class)))
            }
    )
    public CommonResult<ReviewOutputDTO> create(ReviewInputDTO dto) {
        return super.create(dto);
    }

    @Override
    @Operation(hidden = true)
    public CommonResult<ReviewOutputDTO> update(ReviewInputDTO dto, Long id) {
        return super.update(dto, id);
    }

    @Override
    @Operation(hidden = true)
    public CommonResult<Void> deleteById(Long id) {
        return super.deleteById(id);
    }

    @Override
    @Operation(hidden = true)
    public CommonResult<ReviewOutputDTO> findById(Long id) {
        return super.findById(id);
    }
}
