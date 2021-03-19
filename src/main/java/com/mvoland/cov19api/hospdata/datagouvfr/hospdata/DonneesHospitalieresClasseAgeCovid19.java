package com.mvoland.cov19api.hospdata.datagouvfr.hospdata;

import com.mvoland.cov19api.hospdata.data.types.AgeGroup;

import java.time.LocalDate;
import java.util.Objects;

public class DonneesHospitalieresClasseAgeCovid19 {

    private Integer reg;
    private AgeGroup cl_age90;
    private LocalDate jour;
    private Integer hosp;
    private Integer rea;
    private Integer rad;
    private Integer dc;

    public DonneesHospitalieresClasseAgeCovid19() {
    }

    public DonneesHospitalieresClasseAgeCovid19(Integer reg, AgeGroup cl_age90, LocalDate jour, Integer hosp, Integer rea, Integer rad, Integer dc) {
        this.reg = reg;
        this.cl_age90 = cl_age90;
        this.jour = jour;
        this.hosp = hosp;
        this.rea = rea;
        this.rad = rad;
        this.dc = dc;
    }

    public Integer getReg() {
        return reg;
    }

    public void setReg(Integer reg) {
        this.reg = reg;
    }

    public AgeGroup getCl_age90() {
        return cl_age90;
    }

    public void setCl_age90(AgeGroup cl_age90) {
        this.cl_age90 = cl_age90;
    }

    public LocalDate getJour() {
        return jour;
    }

    public void setJour(LocalDate jour) {
        this.jour = jour;
    }

    public Integer getHosp() {
        return hosp;
    }

    public void setHosp(Integer hosp) {
        this.hosp = hosp;
    }

    public Integer getRea() {
        return rea;
    }

    public void setRea(Integer rea) {
        this.rea = rea;
    }

    public Integer getRad() {
        return rad;
    }

    public void setRad(Integer rad) {
        this.rad = rad;
    }

    public Integer getDc() {
        return dc;
    }

    public void setDc(Integer dc) {
        this.dc = dc;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DonneesHospitalieresClasseAgeCovid19 that = (DonneesHospitalieresClasseAgeCovid19) o;
        return Objects.equals(reg, that.reg) && cl_age90 == that.cl_age90 && Objects.equals(jour, that.jour) && Objects.equals(hosp, that.hosp) && Objects.equals(rea, that.rea) && Objects.equals(rad, that.rad) && Objects.equals(dc, that.dc);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reg, cl_age90, jour, hosp, rea, rad, dc);
    }

    @Override
    public String toString() {
        return "DonneesHospitalieresClasseAgeCovid19{" +
                "reg=" + reg +
                ", cl_age90=" + cl_age90 +
                ", jour=" + jour +
                ", hosp=" + hosp +
                ", rea=" + rea +
                ", rad=" + rad +
                ", dc=" + dc +
                '}';
    }
}
