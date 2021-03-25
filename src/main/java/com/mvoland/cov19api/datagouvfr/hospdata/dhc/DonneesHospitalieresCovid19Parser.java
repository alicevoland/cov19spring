package com.mvoland.cov19api.datagouvfr.hospdata.dhc;

import com.mvoland.cov19api.datagouvfr.common.ValueParser;
import org.springframework.stereotype.Component;

@Component
public class DonneesHospitalieresCovid19Parser implements ValueParser<DonneesHospitalieresCovid19> {
    @Override
    public DonneesHospitalieresCovid19 parse(String[] rowValues) {
        DonneesHospitalieresCovid19 data = new DonneesHospitalieresCovid19();
        data.setDep(rowValues[0]);
        data.setSexe(rowValues[1]);
        data.setJour(rowValues[2]);
        data.setHosp(Integer.parseInt((rowValues[3])));
        data.setRea(Integer.parseInt((rowValues[4])));
        data.setRad(Integer.parseInt((rowValues[5])));
        data.setDc(Integer.parseInt((rowValues[6])));
        return data;
    }
}
