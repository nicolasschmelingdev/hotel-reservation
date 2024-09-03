package com.ntconsult.hotelreservation.infrastructure.api;

import com.ntconsult.hotelreservation.domain.model.GenericEntity;
import com.ntconsult.hotelreservation.domain.repository.GenericRepository;
import com.ntconsult.hotelreservation.domain.service.GenericService;
import com.ntconsult.hotelreservation.infrastructure.dto.CommonResult;
import com.ntconsult.hotelreservation.infrastructure.dto.ErrorDetailsDTO;
import com.ntconsult.hotelreservation.infrastructure.dto.GenericDto;
import com.ntconsult.hotelreservation.infrastructure.dto.PageFilters;
import com.ntconsult.hotelreservation.infrastructure.dto.PageResult;
import com.ntconsult.hotelreservation.infrastructure.dto.Sorter;
import com.ntconsult.hotelreservation.util.Util;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.Getter;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
public abstract class GenericResource<T extends GenericEntity<T>, I, D extends GenericDto, V, R extends GenericRepository<T, I>> {

    private final GenericService<T, I, D, V, R> service;

    protected GenericResource(GenericService<T, I, D, V, R> service) {
        this.service = service;
    }


    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.CREATED)
    @Operation(
            summary = "Criar o registro",
            description = "Informar os dados do Payload corretamente para criar um novo registro",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Cadastro efetuado com sucesso", content = @Content(schema = @Schema(implementation = CommonResult.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição mal formada", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Recurso não autorizado", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class)))
            }
    )
    public CommonResult<V> create(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação dos dados de entrada do registro", required = true) @RequestBody @Validated D dto
    ) {
        return CommonResult.success(Util.retornaMensagem("cadastro.sucesso2"), getService().create(dto));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(
            summary = "Editar o registro",
            description = "Informar um identificador válido e dados do Payload corretamente para editar o registro",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Atualização efetuada com sucesso", content = @Content(schema = @Schema(implementation = CommonResult.class))),
                    @ApiResponse(responseCode = "400", description = "Requisição mal formada", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "401", description = "Recurso não autorizado", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno no servidor", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class)))
            }
    )
    public CommonResult<V> update(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Representação dos dados de entrada do registro", required = true) @RequestBody @Validated D dto,
            @Parameter(description = "ID do registro", example = "1", required = true) @PathVariable("id") I id
    ) {
        return CommonResult.success(Util.retornaMensagem("cadastro.sucesso3"), getService().update(dto, id));
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(
            summary = "Remover o registro",
            description = "Informar um identificador válido para excluir o registro",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Registro removido com sucesso"),
                    @ApiResponse(responseCode = "401", description = "Recurso não autorizado", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class)))
            }
    )

    public CommonResult<Void> deleteById(
            @Parameter(description = "ID do registro", example = "1", required = true) @PathVariable("id") I id
    ) {
        getService().deleteById(id);
        return CommonResult.success(Util.retornaMensagem("cadastro.sucesso1"));
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    @Operation(
            summary = "Consultar o registro",
            description = "Informar um identificador válido para consultar o registro",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Registro encontrado", content = @Content(schema = @Schema(implementation = CommonResult.class))),
                    @ApiResponse(responseCode = "401", description = "Recurso não autorizado", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class))),
                    @ApiResponse(responseCode = "404", description = "Recurso não encontrado", content = @Content(schema = @Schema(implementation = ErrorDetailsDTO.class)))
            }
    )
    public CommonResult<V> findById(
            @Parameter(description = "ID do registro", example = "1", required = true) @PathVariable("id") final I id
    ) {
        return CommonResult.success(getService().findByIdDto(id));
    }

    protected CommonResult<PageResult<V>> listRecords(Specification<T> espec, PageFilters pageFilters) {
        return listRecords(espec, pageFilters.getPageNumber(), pageFilters.getPagesSize(), pageFilters.getSortBy(), pageFilters.getSortDirection());
    }

    public CommonResult<PageResult<V>> listRecords(
            Specification<T> espec,
            Integer page,
            Integer limit,
            String sortBy,
            Sorter.Direction sortDirection) {
        PageResult<V> result = getService().getIndex(espec, page, limit, sortBy, sortDirection);
        return CommonResult.success(result);
    }

}

