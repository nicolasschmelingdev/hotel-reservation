package com.ntconsult.hotelreservation.handler.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;


@Data
@EqualsAndHashCode(callSuper = true)
public class EntityNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = -6014796028340788682L;

    private Integer code = 400;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
