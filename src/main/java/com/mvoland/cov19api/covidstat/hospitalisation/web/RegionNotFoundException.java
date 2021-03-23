package com.mvoland.cov19api.covidstat.hospitalisation.web;

class RegionNotFoundException extends RuntimeException {

    public RegionNotFoundException(Integer regionNumber) {
        super("Could not find region " + regionNumber);
    }
}