package com.mvoland.cov19api.covidstat.locality.exception;

public class RegionNotFoundException extends RuntimeException {

    public RegionNotFoundException(String region) {
        super("Could not find region " + region);
    }
}