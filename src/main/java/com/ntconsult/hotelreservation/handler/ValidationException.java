package com.ntconsult.hotelreservation.handler;

import java.io.Serial;

public abstract class ValidationException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 7418662017205321273L;

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }

    public abstract String getProperty();

    public abstract Object[] getObjects();

}
