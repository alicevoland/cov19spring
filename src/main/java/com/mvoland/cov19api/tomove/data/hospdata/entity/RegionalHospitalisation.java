package com.mvoland.cov19api.tomove.data.hospdata.entity;

import com.mvoland.cov19api.common.type.AgeGroup;
import com.mvoland.cov19api.covidstat.locality.data.Region;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class RegionalHospitalisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Region region;
    private LocalDate noticeDate;

    @Column
    @Enumerated(EnumType.STRING)
    private AgeGroup ageGroup;
    private Integer currentHospitalizedCount;
    private Integer currentIntensiveCareCount;
    private Integer currentRadiationCount;
    private Integer currentDeathCount;

    public RegionalHospitalisation() {
    }

    public RegionalHospitalisation(Region region, LocalDate noticeDate, AgeGroup ageGroup,
                                   Integer currentHospitalizedCount, Integer currentIntensiveCareCount,
                                   Integer currentRadiationCount, Integer currentDeathCount) {
        this.region = region;
        this.noticeDate = noticeDate;
        this.ageGroup = ageGroup;
        this.currentHospitalizedCount = currentHospitalizedCount;
        this.currentIntensiveCareCount = currentIntensiveCareCount;
        this.currentRadiationCount = currentRadiationCount;
        this.currentDeathCount = currentDeathCount;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionalHospitalisation that = (RegionalHospitalisation) o;
        return Objects.equals(region, that.region) && Objects.equals(noticeDate, that.noticeDate) && ageGroup == that.ageGroup && Objects.equals(currentHospitalizedCount, that.currentHospitalizedCount) && Objects.equals(currentIntensiveCareCount, that.currentIntensiveCareCount) && Objects.equals(currentRadiationCount, that.currentRadiationCount) && Objects.equals(currentDeathCount, that.currentDeathCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(region, noticeDate, ageGroup, currentHospitalizedCount, currentIntensiveCareCount, currentRadiationCount, currentDeathCount);
    }

    @Override
    public String toString() {
        return "RegionalHospitalisation{" +
                "region=" + region +
                ", noticeDate=" + noticeDate +
                ", ageGroup=" + ageGroup +
                ", currentHospitalizedCount=" + currentHospitalizedCount +
                ", currentIntensiveCareCount=" + currentIntensiveCareCount +
                ", currentRadiationCount=" + currentRadiationCount +
                ", currentDeathCount=" + currentDeathCount +
                '}';
    }
}
