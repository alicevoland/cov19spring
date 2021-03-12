package com.mvoland.cov19api.data.entity;

import javax.persistence.Entity;

@Entity
public class RegionIntensiveCareAdmission {

    private String jour;
    private String nomReg;
    private Integer numReg;
    private Integer incid_rea;

    public RegionIntensiveCareAdmission() {
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
}
