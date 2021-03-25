package com.mvoland.cov19api.covidstat.hospitalisation.data.entity;

import com.mvoland.cov19api.covidstat.locality.data.Department;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DepartmentalNewHospitalisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Department department;
    private LocalDate noticeDate;
    private Integer newHospitalizedCount;
    private Integer newIntensiveCareCount;
    private Integer newRadiationCount;
    private Integer newDeathCount;

    public DepartmentalNewHospitalisation() {
    }

    public DepartmentalNewHospitalisation(Department department, LocalDate noticeDate,
                                          Integer newHospitalizedCount, Integer newIntensiveCareCount,
                                          Integer newRadiationCount, Integer newDeathCount) {
        this.setDepartement(department);
        this.setNoticeDate(noticeDate);
        this.setNewHospitalizedCount(newHospitalizedCount);
        this.setNewIntensiveCareCount(newIntensiveCareCount);
        this.setNewRadiationCount(newRadiationCount);
        this.setNewDeathCount(newDeathCount);
    }


    public Department getDepartement() {
        return department;
    }

    public void setDepartement(Department department) {
        this.department = department;
    }

    public LocalDate getNoticeDate() {
        return noticeDate;
    }

    public void setNoticeDate(LocalDate noticeDate) {
        this.noticeDate = noticeDate;
    }


    public Integer getNewHospitalizedCount() {
        return newHospitalizedCount;
    }

    public void setNewHospitalizedCount(Integer currentHospitalizedCount) {
        this.newHospitalizedCount = currentHospitalizedCount;
    }

    public Integer getNewIntensiveCareCount() {
        return newIntensiveCareCount;
    }

    public void setNewIntensiveCareCount(Integer currentIntensiveCareCount) {
        this.newIntensiveCareCount = currentIntensiveCareCount;
    }

    public Integer getNewRadiationCount() {
        return newRadiationCount;
    }

    public void setNewRadiationCount(Integer currentRadiationCount) {
        this.newRadiationCount = currentRadiationCount;
    }

    public Integer getNewDeathCount() {
        return newDeathCount;
    }

    public void setNewDeathCount(Integer currentDeathCount) {
        this.newDeathCount = currentDeathCount;
    }
}
