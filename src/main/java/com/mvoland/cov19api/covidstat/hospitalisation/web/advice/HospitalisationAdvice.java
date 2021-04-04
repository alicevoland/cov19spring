package com.mvoland.cov19api.covidstat.hospitalisation.web.advice;

import com.mvoland.cov19api.covidstat.hospitalisation.exception.HospitalisationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
class HospitalisationAdvice {

    @ResponseBody
    @ExceptionHandler(HospitalisationNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String regionNotFoundHandler(HospitalisationNotFoundException ex) {
        return ex.getMessage();
    }

}