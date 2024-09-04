package com.ntconsult.hotelreservation.handler;


import com.ntconsult.hotelreservation.handler.exception.UnprocessableEntityException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public interface ExceptionHandler {

    boolean accept(HttpServletRequest req);

    void onUnprocessableEntityException(HttpServletRequest req, HttpServletResponse res, UnprocessableEntityException ex) throws ServletException, IOException;
}
