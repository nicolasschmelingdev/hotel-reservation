package com.ntconsult.hotelreservation.handler.factory;

import com.ntconsult.hotelreservation.handler.ExceptionHandler;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;

@Component
public class ExceptionHandlerFactory implements Serializable {

    @Serial
    private static final long serialVersionUID = -912947657989836789L;
    private final Collection<ExceptionHandler> handlers;

    @Autowired
    public ExceptionHandlerFactory(Collection<ExceptionHandler> handlers) {
        this.handlers = handlers;
    }

    public ExceptionHandler get(HttpServletRequest req) {
        return handlers
                .stream()
                .filter(handler -> handler.accept(req))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Exception handler not found"));
    }
}
