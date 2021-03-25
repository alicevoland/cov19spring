package com.mvoland.cov19api.datagouvfr.hospdata.dhnc;

import com.mvoland.cov19api.datagouvfr.common.ValueParser;
import org.springframework.stereotype.Component;

@Component
public class DonneesHospitalieresNouveauxCovid19Parser implements ValueParser<DonneesHospitalieresNouveauxCovid19> {
    @Override
    public DonneesHospitalieresNouveauxCovid19 parse(String[] rowValues) throws IllegalArgumentException {
        DonneesHospitalieresNouveauxCovid19 data = new DonneesHospitalieresNouveauxCovid19();
        data.setDep(rowValues[0]);
        data.setJour(rowValues[1]);
        data.setIncid_hosp(Integer.parseInt(rowValues[2]));
        data.setIncid_rea(Integer.parseInt((rowValues[3])));
        data.setIncid_dc(Integer.parseInt(rowValues[4]));
        data.setIncid_rad(Integer.parseInt((rowValues[5])));
        return data;
    }
}
