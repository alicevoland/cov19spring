package com.mvoland.cov19api.datagouvfr.hospdata.dhnc;

public class DonneesHospitalieresNouveauxCovid19 {


    private String dep;
    private String jour;
    private Integer incid_hosp;
    private Integer incid_rea;
    private Integer incid_rad;
    private Integer incid_dc;

    public DonneesHospitalieresNouveauxCovid19() {
    }

    public DonneesHospitalieresNouveauxCovid19(String dep, String jour, Integer incid_hosp, Integer incid_rea, Integer incid_rad, Integer incid_dc) {
        this.dep = dep;
        this.jour = jour;
        this.incid_hosp = incid_hosp;
        this.incid_rea = incid_rea;
        this.incid_rad = incid_rad;
        this.incid_dc = incid_dc;
    }

    public String getDep() {
        return dep;
    }

    public void setDep(String dep) {
        this.dep = dep;
    }

    public String getJour() {
        return jour;
    }

    public void setJour(String jour) {
        this.jour = jour;
    }

    public Integer getIncid_hosp() {
        return incid_hosp;
    }

    public void setIncid_hosp(Integer incid_hosp) {
        this.incid_hosp = incid_hosp;
    }

    public Integer getIncid_rea() {
        return incid_rea;
    }

    public void setIncid_rea(Integer incid_rea) {
        this.incid_rea = incid_rea;
    }

    public Integer getIncid_rad() {
        return incid_rad;
    }

    public void setIncid_rad(Integer incid_rad) {
        this.incid_rad = incid_rad;
    }

    public Integer getIncid_dc() {
        return incid_dc;
    }

    public void setIncid_dc(Integer incid_dc) {
        this.incid_dc = incid_dc;
    }

    @Override
    public String toString() {
        return "DonneesHospitalieresNouveauxCovid19{" +
                "dep='" + dep + '\'' +
                ", jour='" + jour + '\'' +
                ", incid_hosp=" + incid_hosp +
                ", incid_rea=" + incid_rea +
                ", incid_rad=" + incid_rad +
                ", incid_dc=" + incid_dc +
                '}';
    }
}
