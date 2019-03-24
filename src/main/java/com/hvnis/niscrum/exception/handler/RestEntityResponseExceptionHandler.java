package com.hvnis.niscrum.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author hvnis
 */
@ControllerAdvice
public class RestEntityResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ RuntimeException.class })
    public Object handleAll(RuntimeException ex) {
        Map<String, Object> data = new HashMap<>();
        data.put("code", HttpStatus.BAD_REQUEST);
        data.put("message", ex.getMessage());
        return data;
    }

    @ExceptionHandler({ Exception.class })
    public Object handleAll(Exception ex) {
        Map<String, Object> data = new HashMap<>();
        data.put("code", HttpStatus.BAD_REQUEST);
        data.put("message", ex.getMessage());
        return data;
    }

}
