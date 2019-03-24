package com.hvnis.niscrum.controller;

import org.springframework.web.bind.annotation.RestController;

/**
 * @author hvnis
 */
@RestController
public abstract class AbstractController {

//    @ExceptionHandler
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public ErrorResponse handleInvalidTopTalentDataException(
//            MethodArgumentNotValidException methodArgumentNotValidException) {
//        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), methodArgumentNotValidException.getMessage());
//    }
}
