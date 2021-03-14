package com.mvoland.cov19api.data.entity;

import javax.persistence.Entity;

@Entity
public class DonneesHospitalieresClasseAgeCovid19Entity {

    private String reg;
    private String cl_age90;
    private String jour;
    private Integer hosp;
    private Integer rea;
    private Integer rad;
    private Integer dc;

    public DonneesHospitalieresClasseAgeCovid19Entity() {
    }

    public String getReg() {
        return reg;
    }

    public void setReg(String reg) {
        this.reg = reg;
    }

    public String getCl_age90() {
        return cl_age90;
    }

    public void setCl_age90(String cl_age90) {
        this.cl_age90 = cl_age90;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
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
    public String toString() {
        return "DonneesHospitalieresClasseAgeCovid19Entity{" +
                "reg='" + reg + '\'' +
                ", cl_age90='" + cl_age90 + '\'' +
                ", jour='" + jour + '\'' +
                ", hosp=" + hosp +
                ", rea=" + rea +
                ", rad=" + rad +
                ", dc=" + dc +
                '}';
    }
}
