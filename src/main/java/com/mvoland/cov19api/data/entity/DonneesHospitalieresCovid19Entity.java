package com.mvoland.cov19api.data.entity;

import javax.persistence.Entity;

@Entity
public class DonneesHospitalieresCovid19Entity {

    private String dep;
    private String sexe;
    private String jour;
    private Integer hosp;
    private Integer rea;
    private Integer rad;
    private Integer dc;

    public DonneesHospitalieresCovid19Entity() {
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
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
        return "DonneesHospitalieresCovid19Entity{" +
                "dep='" + dep + '\'' +
                ", sexe='" + sexe + '\'' +
                ", jour='" + jour + '\'' +
                ", hosp=" + hosp +
                ", rea=" + rea +
                ", rad=" + rad +
                ", dc=" + dc +
                '}';
    }
}
