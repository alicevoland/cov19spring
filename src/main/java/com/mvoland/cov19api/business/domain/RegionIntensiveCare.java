package com.mvoland.cov19api.business.domain;

import java.time.LocalDate;
import java.util.SortedMap;

public class RegionIntensiveCare {

    private String regionName;
    private Integer regionNumber;
    private SortedMap<LocalDate, Integer> intensiveCareAdmissions;

    public RegionIntensiveCare(String regionName, Integer regionNumber, SortedMap<LocalDate, Integer> intensiveCareAdmissions) {
        this.regionName = regionName;
        this.regionNumber = regionNumber;
        this.intensiveCareAdmissions = intensiveCareAdmissions;
    }

    public String getRegionName() {
        return regionName;
    }

    public Integer getRegionNumber() {
        return regionNumber;
    }

    public SortedMap<LocalDate, Integer> getIntensiveCareAdmissions() {
        return intensiveCareAdmissions;
    }
}
