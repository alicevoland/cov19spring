package com.mvoland.cov19api.data.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class RegionalIntensiveCareAdmission {
    @Id
    @GeneratedValue
    private Long id;

    private Integer regionNumber;
    private LocalDate noticeDate;
    private Integer intensiveCareAdmissionCount;

    public RegionalIntensiveCareAdmission() {
    }

    public RegionalIntensiveCareAdmission(Integer regionNumber, LocalDate noticeDate, Integer intensiveCareAdmissionCount) {
        this.setRegionNumber(regionNumber);
        this.setNoticeDate(noticeDate);
        this.setIntensiveCareAdmissionCount(intensiveCareAdmissionCount);
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

    public Integer getIntensiveCareAdmissionCount() {
        return intensiveCareAdmissionCount;
    }

    public void setIntensiveCareAdmissionCount(Integer intensiveCareAdmissionCount) {
        this.intensiveCareAdmissionCount = intensiveCareAdmissionCount;
    }

    @Override
    public String toString() {
        return "RegionalIntensiveCareAdmission{" +
                "regionNumber=" + getRegionNumber() +
                ", noticeDate=" + getNoticeDate() +
                ", intensiveCareAdmissionCountintesiveCareAdmissionCount=" + getIntensiveCareAdmissionCount() +
                '}';
    }
}
