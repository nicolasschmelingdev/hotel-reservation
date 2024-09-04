package com.ntconsult.hotelreservation.infrastructure.api;

import com.ntconsult.hotelreservation.domain.model.Reservation;
import com.ntconsult.hotelreservation.domain.repository.ReservationRepository;
import com.ntconsult.hotelreservation.domain.service.ReservationService;
import com.ntconsult.hotelreservation.infrastructure.dto.CommonResult;
import com.ntconsult.hotelreservation.infrastructure.dto.ErrorDetailsDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.input.ReservationInputDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.output.ReservationOutputDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/reservations")
public class ReservationResource extends GenericResource<Reservation, Long, ReservationInputDTO, ReservationOutputDTO,
        ReservationRepository> {

    protected ReservationResource(ReservationService service) {
        super(service);
    }

    @Override
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(
            summary = "Realiza uma reserva",
            description = "Informar os dados da hospedagem corretamente para criar uma nova reserva",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cadastro efetuado com sucesso", content = @Content(schema = @Schema(implementation = CommonResult.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição mal formada", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Recurso não autorizado", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class)))
            }
    )
    public CommonResult<ReservationOutputDTO> create(ReservationInputDTO dto) {
        return super.create(dto);
    }

    @Override
    @Operation(
            summary = "Editar uma reserva",
            description = "Informar o id da reserva corretamente para edita-la",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Atualização efetuada com sucesso", content = @Content(schema = @Schema(implementation = CommonResult.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição mal formada", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Recurso não autorizado", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class)))
            }
    )
    public CommonResult<ReservationOutputDTO> update(ReservationInputDTO dto, Long id) {
        return super.update(dto, id);
    }

    @Override
    @Operation(
            summary = "Consultar uma reserva",
            description = "Informar o id da reserva corretamente para consulta-a",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Registro encontrado", content = @Content(schema = @Schema(implementation = CommonResult.class))),
                    @ApiResponse(responseCode = "401", description = "Recurso não autorizado", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class)))
            }
    )
    public CommonResult<ReservationOutputDTO> findById(Long id) {
        return super.findById(id);
    }

    @Override
    @Operation(hidden = true)
    public CommonResult<Void> deleteById(Long id) {
        return super.deleteById(id);
    }
}
