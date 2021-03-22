package com.mvoland.cov19api.web.hospdata;

class RegionNotFoundException extends RuntimeException {

    public RegionNotFoundException(Integer regionNumber) {
        super("Could not find region " + regionNumber);
    }
}