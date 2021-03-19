package com.mvoland.cov19api.hospdata.data.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
public class RegionalIntensiveCareAdmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Region region;

    private LocalDate noticeDate;
    private Integer intensiveCareAdmissionCount;

    public RegionalIntensiveCareAdmission() {
    }

    public RegionalIntensiveCareAdmission(Region region, LocalDate noticeDate, Integer intensiveCareAdmissionCount) {
        this.region = region;
        this.setNoticeDate(noticeDate);
        this.setIntensiveCareAdmissionCount(intensiveCareAdmissionCount);
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

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    @Override
    public String toString() {
        return "RegionalIntensiveCareAdmission{" +
                "region=" + region +
                ", noticeDate=" + noticeDate +
                ", intensiveCareAdmissionCount=" + intensiveCareAdmissionCount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegionalIntensiveCareAdmission that = (RegionalIntensiveCareAdmission) o;
        return Objects.equals(region, that.region) && Objects.equals(noticeDate, that.noticeDate) && Objects.equals(intensiveCareAdmissionCount, that.intensiveCareAdmissionCount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(noticeDate, intensiveCareAdmissionCount);
    }
}
