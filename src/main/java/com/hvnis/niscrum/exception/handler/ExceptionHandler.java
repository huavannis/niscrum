package com.hvnis.niscrum.exception.handler;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import com.hvnis.niscrum.helper.JsonHelper;

import lombok.AllArgsConstructor;

/**
 * @author hvnis
 */
@AllArgsConstructor
@Component
public class ExceptionHandler {

    private final JsonHelper jsonHelper;

    public ExceptionData buildExceptionData(HttpServletRequest request, HttpStatus status, Throwable throwable) {
        return ExceptionData.builder().timestamp(new Date()).status(status.value()).error(status.getReasonPhrase())
                .message(throwable.getMessage()).path(request.getServletPath()).build();
    }

    public ExceptionData buildExceptionData(HttpServletRequest request, HttpStatus status, String error,
            String message) {
        return ExceptionData.builder().timestamp(new Date()).status(status.value()).error(error).message(message)
                .path(request.getServletPath()).build();
    }

    public void handle(HttpServletRequest request, HttpServletResponse response, HttpStatus status, Throwable throwable)
            throws IOException {
        handle(request, response, status, status.getReasonPhrase(), throwable.getMessage());
    }

    public void handle(HttpServletRequest request, HttpServletResponse response, HttpStatus status, String message)
            throws IOException {
        handle(request, response, status, status.getReasonPhrase(), message);
    }

    public void handle(HttpServletRequest request, HttpServletResponse response, HttpStatus status, String error,
            String message) throws IOException {
        ExceptionData exceptionData = buildExceptionData(request, status, error, message);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status.value());
        response.getOutputStream().println(jsonHelper.parseToJson(exceptionData));
    }
}
