package com.mvoland.cov19api.tomove.datagouvfr.hospdata;

import java.time.LocalDate;
import java.util.Objects;

public class CovidHospitIncidReg {

    private LocalDate jour;
    private String nomReg;
    private Integer numReg;
    private Integer incid_rea;

    public CovidHospitIncidReg() {
    }

    public CovidHospitIncidReg(LocalDate jour, String nomReg, Integer numReg, Integer incid_rea) {
        this.jour = jour;
        this.nomReg = nomReg;
        this.numReg = numReg;
        this.incid_rea = incid_rea;
    }

    public LocalDate getJour() {
        return jour;
    }

    public void setJour(LocalDate jour) {
        this.jour = jour;
    }

    public String getNomReg() {
        return nomReg;
    }

    public void setNomReg(String nomReg) {
        this.nomReg = nomReg;
    }

    public Integer getNumReg() {
        return numReg;
    }

    public void setNumReg(Integer numReg) {
        this.numReg = numReg;
    }

    public Integer getIncid_rea() {
        return incid_rea;
    }

    public void setIncid_rea(Integer incid_rea) {
        this.incid_rea = incid_rea;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CovidHospitIncidReg that = (CovidHospitIncidReg) o;
        return Objects.equals(jour, that.jour) && Objects.equals(nomReg, that.nomReg) && Objects.equals(numReg, that.numReg) && Objects.equals(incid_rea, that.incid_rea);
    }

    @Override
    public int hashCode() {
        return Objects.hash(jour, nomReg, numReg, incid_rea);
    }

    @Override
    public String toString() {
        return "CovidHospitIncidReg{" +
                "jour=" + jour +
                ", nomReg='" + nomReg + '\'' +
                ", numReg=" + numReg +
                ", incid_rea=" + incid_rea +
                '}';
    }
}
