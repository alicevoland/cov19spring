package com.mvoland.cov19api.datagouvfr.hospdata.dhc;

import com.mvoland.cov19api.common.type.Sex;

import java.time.LocalDate;

public class DonneesHospitalieresCovid19 {


    private String dep;
    private Sex sexe;
    private LocalDate jour;
    private Integer hosp;
    private Integer rea;
    private Integer rad;
    private Integer dc;

    public DonneesHospitalieresCovid19() {
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public Sex getSexe() {
        return sexe;
    }

    public void setSexe(Sex sexe) {
        this.sexe = sexe;
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
    public String toString() {
        return "DonneesHospitalieresCovid19{" +
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
