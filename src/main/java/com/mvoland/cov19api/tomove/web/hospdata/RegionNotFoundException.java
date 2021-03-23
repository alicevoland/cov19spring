package com.mvoland.cov19api.tomove.web.hospdata;

class RegionNotFoundException extends RuntimeException {

    public RegionNotFoundException(Integer regionNumber) {
        super("Could not find region " + regionNumber);
    }
}