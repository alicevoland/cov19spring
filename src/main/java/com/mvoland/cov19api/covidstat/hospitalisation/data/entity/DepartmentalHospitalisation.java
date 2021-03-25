package com.mvoland.cov19api.covidstat.hospitalisation.data.entity;

import com.mvoland.cov19api.common.type.Sex;
import com.mvoland.cov19api.covidstat.locality.data.Department;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DepartmentalHospitalisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Department department;
    private LocalDate noticeDate;

    @Column
    @Enumerated(EnumType.STRING)
    private Sex sex;
    private Integer currentHospitalizedCount;
    private Integer currentIntensiveCareCount;
    private Integer currentRadiationCount;
    private Integer currentDeathCount;

    public DepartmentalHospitalisation() {
    }

    public DepartmentalHospitalisation(Department department, LocalDate noticeDate, Sex sex,
                                       Integer currentHospitalizedCount, Integer currentIntensiveCareCount,
                                       Integer currentRadiationCount, Integer currentDeathCount) {
        this.setDepartement(department);
        this.setNoticeDate(noticeDate);
        this.setSex(sex);
        this.setCurrentHospitalizedCount(currentHospitalizedCount);
        this.setCurrentIntensiveCareCount(currentIntensiveCareCount);
        this.setCurrentRadiationCount(currentRadiationCount);
        this.setCurrentDeathCount(currentDeathCount);
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

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
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
