package com.mvoland.cov19api.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class RegionalHospitalisation {
    @Id
    @GeneratedValue
    private Long id;

    private Integer regionNumber;
    private LocalDate noticeDate;
    private AgeGroup ageGroup;
    private Integer currentHospitalizedCount;
    private Integer currentIntensiveCareCount;
    private Integer currentRadiationCount;
    private Integer currentDeathCount;

    public RegionalHospitalisation() {
    }

    public RegionalHospitalisation(Integer regionNumber, LocalDate noticeDate, AgeGroup ageGroup,
                                   Integer currentHospitalizedCount, Integer currentIntensiveCareCount,
                                   Integer currentRadiationCount, Integer currentDeathCount) {
        this.regionNumber = regionNumber;
        this.noticeDate = noticeDate;
        this.ageGroup = ageGroup;
        this.currentHospitalizedCount = currentHospitalizedCount;
        this.currentIntensiveCareCount = currentIntensiveCareCount;
        this.currentRadiationCount = currentRadiationCount;
        this.currentDeathCount = currentDeathCount;
    }

    public Integer getRegionNumber() {
        return regionNumber;
    }

    public void setRegionNumber(Integer regionNumber) {
        this.regionNumber = regionNumber;
    }

    public LocalDate getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(LocalDate noticeDate) {
        this.noticeDate = noticeDate;
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }

    public Integer getCurrentHospitalizedCount() {
        return currentHospitalizedCount;
    }

    public void setCurrentHospitalizedCount(Integer currentHospitalizedCount) {
        this.currentHospitalizedCount = currentHospitalizedCount;
    }

    public Integer getCurrentIntensiveCareCount() {
        return currentIntensiveCareCount;
    }

    public void setCurrentIntensiveCareCount(Integer currentIntensiveCareCount) {
        this.currentIntensiveCareCount = currentIntensiveCareCount;
    }

    public Integer getCurrentRadiationCount() {
        return currentRadiationCount;
    }

    public void setCurrentRadiationCount(Integer currentRadiationCount) {
        this.currentRadiationCount = currentRadiationCount;
    }

    public Integer getCurrentDeathCount() {
        return currentDeathCount;
    }

    public void setCurrentDeathCount(Integer currentDeathCount) {
        this.currentDeathCount = currentDeathCount;
    }
}
