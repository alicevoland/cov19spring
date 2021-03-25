package com.mvoland.cov19api.datagouvfr.hospdata.dhnc;

import com.mvoland.cov19api.datagouvfr.common.ParsingUtils;
import com.mvoland.cov19api.datagouvfr.common.ValueParser;
import org.springframework.stereotype.Component;

@Component
public class DonneesHospitalieresNouveauxCovid19Parser implements ValueParser<DonneesHospitalieresNouveauxCovid19> {
    @Override
    public DonneesHospitalieresNouveauxCovid19 parse(String[] rowValues) throws IllegalArgumentException {
        DonneesHospitalieresNouveauxCovid19 data = new DonneesHospitalieresNouveauxCovid19();
        data.setDep(rowValues[0]);
        data.setJour(ParsingUtils.parseDateOrThrow(rowValues[1]));
        data.setIncid_hosp(ParsingUtils.parseIntegerOrDefault(rowValues[2], 0));
        data.setIncid_rea(ParsingUtils.parseIntegerOrDefault((rowValues[3]), 0));
        data.setIncid_dc(ParsingUtils.parseIntegerOrDefault(rowValues[4], 0));
        data.setIncid_rad(ParsingUtils.parseIntegerOrDefault(rowValues[5], 0));
        return data;
    }
}
