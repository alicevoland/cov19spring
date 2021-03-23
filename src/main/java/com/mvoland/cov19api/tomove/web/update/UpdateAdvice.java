package com.mvoland.cov19api.tomove.web.update;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class UpdateAdvice {

    @ResponseBody
    @ExceptionHandler(CannotUpdateException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    String cannotUpdateHandler(CannotUpdateException ex) {
        return ex.getMessage();
    }
}