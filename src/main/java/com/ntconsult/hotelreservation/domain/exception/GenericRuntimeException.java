package com.ntconsult.hotelreservation.domain.exception;

public class GenericRuntimeException extends RuntimeException {

    public GenericRuntimeException() {
        super();
    }

    public GenericRuntimeException(String message) {
        super(message);
    }

    public GenericRuntimeException(RuntimeException e) {
        super(e);
    }

}
