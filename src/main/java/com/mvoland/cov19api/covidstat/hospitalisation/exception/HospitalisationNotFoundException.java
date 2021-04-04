package com.mvoland.cov19api.covidstat.hospitalisation.exception;

public class HospitalisationNotFoundException extends RuntimeException {
    public HospitalisationNotFoundException(String message) {
        super(message);
    }

    public HospitalisationNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
