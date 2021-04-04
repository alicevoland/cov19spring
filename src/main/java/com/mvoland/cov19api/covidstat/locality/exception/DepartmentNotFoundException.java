package com.mvoland.cov19api.covidstat.locality.exception;

public class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(String department) {
        super("Could not find department " + department);
    }
}