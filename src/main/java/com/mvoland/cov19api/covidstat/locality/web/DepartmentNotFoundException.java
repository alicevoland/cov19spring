package com.mvoland.cov19api.covidstat.locality.web;

class DepartmentNotFoundException extends RuntimeException {

    public DepartmentNotFoundException(String department) {
        super("Could not find department " + department);
    }
}