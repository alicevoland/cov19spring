package com.mvoland.cov19api.datagouvfr.hospdata;

public class CovidHospitIncidReg {

    private String jour;
    private String nomReg;
    private Integer numReg;
    private Integer incid_rea;

    public CovidHospitIncidReg() {
    }

    public CovidHospitIncidReg(String jour, String nomReg, Integer numReg, Integer incid_rea) {
        this.jour = jour;
        this.nomReg = nomReg;
        this.numReg = numReg;
        this.incid_rea = incid_rea;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
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
    public String toString() {
        return "CovidHospitIncidReg{" +
                "jour='" + jour + '\'' +
                ", nomReg='" + nomReg + '\'' +
                ", numReg=" + numReg +
                ", incid_rea=" + incid_rea +
                '}';
    }
}
