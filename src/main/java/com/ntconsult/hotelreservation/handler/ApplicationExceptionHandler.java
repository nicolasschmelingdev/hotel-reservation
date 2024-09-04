package com.ntconsult.hotelreservation.handler;


import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.fasterxml.jackson.databind.exc.PropertyBindingException;
import com.ntconsult.hotelreservation.handler.exception.BadRequestException;
import com.ntconsult.hotelreservation.handler.exception.BusinessException;
import com.ntconsult.hotelreservation.handler.exception.EntityNotFoundException;
import com.ntconsult.hotelreservation.handler.exception.ForbidenException;
import com.ntconsult.hotelreservation.handler.exception.GenericErrorException;
import com.ntconsult.hotelreservation.handler.exception.InternalServerErrorException;
import com.ntconsult.hotelreservation.handler.exception.UnauthorizedAccessException;
import com.ntconsult.hotelreservation.handler.exception.UnprocessableEntityException;
import com.ntconsult.hotelreservation.handler.factory.ExceptionHandlerFactory;
import com.ntconsult.hotelreservation.infrastructure.dto.ErrorDetailsDTO;
import com.ntconsult.hotelreservation.util.Util;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.TypeMismatchException;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;
import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    private final MessageSource messageSource;
    private final ExceptionHandlerFactory exceptionHandlerFactory;

    public ApplicationExceptionHandler(MessageSource messageSource, ExceptionHandlerFactory exceptionHandlerFactory) {
        this.messageSource = messageSource;
        this.exceptionHandlerFactory = exceptionHandlerFactory;
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(@NotNull HttpMediaTypeNotAcceptableException ex,
                                                                      @NotNull HttpHeaders headers,
                                                                      @NotNull HttpStatusCode status,
                                                                      @NotNull WebRequest request) {
        return ResponseEntity.status(status).headers(headers).build();
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(@NotNull MethodArgumentNotValidException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatusCode status,
                                                                  @NotNull WebRequest request) {
        return handleValidationInternal(ex, headers, status, request, ex.getBindingResult());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUncaught(Exception ex, WebRequest request) {
        log.info("M=Exception", ex);
        HttpStatusCode status = HttpStatus.INTERNAL_SERVER_ERROR;
        ErrorDetailsDTO problem = createProblemBuilder(status, TitleValidationConstants.ERRO_DE_SISTEMA, ex.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());
        return super.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDatabaseIncapableOfOperation(Exception ex, WebRequest request) {
        log.info("M=DatabaseIncapableOfOperation", ex);
        HttpStatusCode status = HttpStatus.CONFLICT;
        ErrorDetailsDTO problem = createProblemBuilder(status, TitleValidationConstants.ERRO_DE_SISTEMA, ex.getMessage(), ((ServletWebRequest) request).getRequest().getRequestURL().toString());
        return super.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(@NotNull NoHandlerFoundException ex,
                                                                   @NotNull HttpHeaders headers,
                                                                   @NotNull HttpStatusCode status,
                                                                   @NotNull WebRequest request) {
        log.info("M=NoHandlerFoundException", ex);
        ErrorDetailsDTO problem = createProblemBuilder(status, TitleValidationConstants.RECURSO_NAO_ENCONTRADO, ex.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());
        return handleExceptionInternal(ex, problem, headers, status, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleTypeMismatch(@NotNull TypeMismatchException ex,
                                                        @NotNull HttpHeaders headers,
                                                        @NotNull HttpStatusCode status,
                                                        @NotNull WebRequest request) {
        log.info("M=TypeMismatchException", ex);
        if (ex instanceof MethodArgumentTypeMismatchException methodArgumentTypeMismatchException) {
            return handleMethodArgumentTypeMismatch(methodArgumentTypeMismatchException, headers, status, request);
        }
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @NotNull
    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(@NotNull HttpMessageNotReadableException ex,
                                                                  @NotNull HttpHeaders headers,
                                                                  @NotNull HttpStatusCode status,
                                                                  @NotNull WebRequest request) {
        log.info("M=HttpMessageNotReadableException", ex);
        Throwable rootCause = ExceptionUtils.getRootCause(ex);
        if (rootCause instanceof InvalidFormatException invalidFormatException) {
            return handleInvalidFormat(invalidFormatException, headers, status, request);
        } else if (rootCause instanceof PropertyBindingException propertyBindingException) {
            return handlePropertyBinding(propertyBindingException, headers, status, request);
        }
        ErrorDetailsDTO problem = createProblemBuilder(status, TitleValidationConstants.MENSAGEM_INCOMPREENSIVEL, ex.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());
        return super.handleExceptionInternal(ex, problem, headers, status, request);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Object> handleAccessDeniedException(AccessDeniedException ex, WebRequest request) {
        log.info("M=AccessDeniedException", ex);
        HttpStatusCode status = HttpStatus.FORBIDDEN;
        ErrorDetailsDTO problem = createProblemBuilder(status, TitleValidationConstants.ACESSO_NEGADO, ex.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());
        return super.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<Object> handleAccessDeniedException(BusinessException ex, WebRequest request) {
        log.info("M=BussinessExcpetion", ex);
        HttpStatusCode status = BAD_REQUEST;
        ErrorDetailsDTO problem = createProblemBuilder(status, TitleValidationConstants.ERRO_NEGOCIO, ex.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());
        return super.handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(final EntityNotFoundException ex, WebRequest request) {
        log.info("M=EntityNotFoundException", ex);
        HttpStatusCode status = NOT_FOUND;
        ErrorDetailsDTO error = createProblemBuilder(status, TitleValidationConstants.ENTIDADE_NAO_ENCONTRADA, ex.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());
        return super.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(GenericErrorException.class)
    public ResponseEntity<Object> handleGenericErrorException(final GenericErrorException ex, WebRequest request) {
        log.info("M=GenericErrorException", ex);
        HttpStatusCode status = UNPROCESSABLE_ENTITY;
        ErrorDetailsDTO error = createProblemBuilder(status, TitleValidationConstants.ERRO_NEGOCIO, ex.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());
        return super.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(UnauthorizedAccessException.class)
    public ResponseEntity<Object> handleUnauthorizedAccessException(final UnauthorizedAccessException ex, WebRequest request) {
        log.info("M=UnauthorizedAccessException", ex);
        HttpStatusCode status = UNAUTHORIZED;
        ErrorDetailsDTO error = createProblemBuilder(status, TitleValidationConstants.SEM_AUTORIZACAO, ex.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());
        return super.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> handleInternalServerErrorException(final InternalServerErrorException ex, WebRequest request) {
        log.info("M=InternalServerErrorException", ex);
        HttpStatusCode status = INTERNAL_SERVER_ERROR;
        ErrorDetailsDTO error = createProblemBuilder(status, TitleValidationConstants.SERVER_ERROR, ex.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());
        return super.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(ForbidenException.class)
    public ResponseEntity<Object> handleForbidenException(final ForbidenException ex, WebRequest request) {
        log.info("M=ForbidenException", ex);
        HttpStatusCode status = FORBIDDEN;
        ErrorDetailsDTO error = createProblemBuilder(status, TitleValidationConstants.SEM_AUTORIZACAO, ex.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());
        return super.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<Object> handleBadRequestException(final BadRequestException ex, WebRequest request) {
        log.info("M=BadRequestException", ex);
        HttpStatusCode status = BAD_REQUEST;
        ErrorDetailsDTO error = createProblemBuilder(status, TitleValidationConstants.ERRO_NEGOCIO, ex.getMessage(),
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());
        return super.handleExceptionInternal(ex, error, new HttpHeaders(), status, request);
    }

    private ResponseEntity<Object> handleValidationInternal(Exception ex, HttpHeaders headers,
                                                            HttpStatusCode status,
                                                            WebRequest request,
                                                            BindingResult bindingResult) {
        String detail = "Um ou mais campos estão inválidos.";
        List<ErrorDetailsDTO.Object> problemObjects = bindingResult.getAllErrors().stream()
                .map(objectError -> {
                    String message;
                    try {
                        message = messageSource.getMessage(Objects.requireNonNull(objectError.getDefaultMessage()), null, Util.recuperaLocale());
                    } catch (NoSuchMessageException e) {
                        message = objectError.getDefaultMessage();
                    }
                    String name = objectError.getObjectName();
                    if (objectError instanceof FieldError fieldError) {
                        name = fieldError.getField();
                    }
                    return ErrorDetailsDTO.Object.builder()
                            .name(name)
                            .userMessage(message)
                            .build();
                }).toList();
        ErrorDetailsDTO problem = createProblemBuilder(status,
                TitleValidationConstants.DADOS_INVALIDOS,
                detail,
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());
        problem.setObjects(problemObjects);
        return super.handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ErrorDetailsDTO createProblemBuilder(HttpStatusCode status, String title, String detail, String contextPah) {
        return ErrorDetailsDTO.builder()
                .timestamp(OffsetDateTime.now())
                .status(status.value())
                .type(contextPah)
                .title(title)
                .detail(detail).build();
    }

    private ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpHeaders headers,
            HttpStatusCode status, WebRequest request) {
        String detail = String.format("O parâmetro de URL '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                ex.getName(), ex.getValue(), Objects.requireNonNull(ex.getRequiredType()).getSimpleName());
        ErrorDetailsDTO problem = createProblemBuilder(status, TitleValidationConstants.PARAMETRO_INVALIDO, detail,
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());
        return super.handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handleInvalidFormat(InvalidFormatException ex,
                                                       HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String path = joinPath(ex.getPath());
        String detail = String.format("A propriedade '%s' recebeu o valor '%s', "
                        + "que é de um tipo inválido. Corrija e informe um valor compatível com o tipo %s.",
                path, ex.getValue(), ex.getTargetType().getSimpleName());
        ErrorDetailsDTO problem = createProblemBuilder(status, TitleValidationConstants.MENSAGEM_INCOMPREENSIVEL, detail,
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());
        return super.handleExceptionInternal(ex, problem, headers, status, request);
    }

    private ResponseEntity<Object> handlePropertyBinding(PropertyBindingException ex,
                                                         HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        String path = joinPath(ex.getPath());
        String detail = String.format("A propriedade '%s' não existe. "
                + "Corrija ou remova essa propriedade e tente novamente.", path);
        ErrorDetailsDTO problem = createProblemBuilder(status, TitleValidationConstants.MENSAGEM_INCOMPREENSIVEL, detail,
                ((ServletWebRequest) request).getRequest().getRequestURL().toString());
        return super.handleExceptionInternal(ex, problem, headers, status, request);
    }

    private String joinPath(List<JsonMappingException.Reference> references) {
        return references.stream()
                .map(JsonMappingException.Reference::getFieldName)
                .collect(Collectors.joining("."));
    }

    @ExceptionHandler(UnprocessableEntityException.class)
    public void onUnprocessableEntityException(HttpServletRequest req, HttpServletResponse res, UnprocessableEntityException ex) throws ServletException, IOException {
        exceptionHandlerFactory.get(req).onUnprocessableEntityException(req, res, ex);
    }

}
