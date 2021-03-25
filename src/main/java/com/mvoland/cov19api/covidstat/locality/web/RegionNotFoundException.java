package com.mvoland.cov19api.covidstat.locality.web;

class RegionNotFoundException extends RuntimeException {

    public RegionNotFoundException(String regionCode) {
        super("Could not find region " + regionCode);
    }
}