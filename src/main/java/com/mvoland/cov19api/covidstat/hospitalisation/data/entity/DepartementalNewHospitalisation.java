package com.mvoland.cov19api.covidstat.hospitalisation.data.entity;

import com.mvoland.cov19api.covidstat.locality.data.Departement;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class DepartementalNewHospitalisation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Departement departement;
    private LocalDate noticeDate;
    private Integer newHospitalizedCount;
    private Integer newIntensiveCareCount;
    private Integer newRadiationCount;
    private Integer newDeathCount;

    public DepartementalNewHospitalisation() {
    }

    public DepartementalNewHospitalisation(Departement departement, LocalDate noticeDate,
                                           Integer newHospitalizedCount, Integer newIntensiveCareCount,
                                           Integer newRadiationCount, Integer newDeathCount) {
        this.setDepartement(departement);
        this.setNoticeDate(noticeDate);
        this.setNewHospitalizedCount(newHospitalizedCount);
        this.setNewIntensiveCareCount(newIntensiveCareCount);
        this.setNewRadiationCount(newRadiationCount);
        this.setNewDeathCount(newDeathCount);
    }


    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
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
