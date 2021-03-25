package com.mvoland.cov19api.datagouvfr.hospdata.dhcac;

import com.mvoland.cov19api.datagouvfr.common.ValueParser;
import com.mvoland.cov19api.datagouvfr.common.ParsingUtils;
import org.springframework.stereotype.Component;

@Component
public class DonneesHospitalieresClasseAgeCovid19Parser implements ValueParser<DonneesHospitalieresClasseAgeCovid19> {
    @Override
    public DonneesHospitalieresClasseAgeCovid19 parse(String[] rowValues) {
        DonneesHospitalieresClasseAgeCovid19 data = new DonneesHospitalieresClasseAgeCovid19();
        data.setReg(rowValues[0]);
        data.setCl_age90(ParsingUtils.parseAgeGroupOrThrow(rowValues[1]));
        data.setJour(ParsingUtils.parseDateOrThrow(rowValues[2]));
        data.setHosp(ParsingUtils.parseIntegerOrDefault(rowValues[3], 0));
        data.setRea(ParsingUtils.parseIntegerOrDefault(rowValues[4], 0));
        data.setRad(ParsingUtils.parseIntegerOrDefault(rowValues[5], 0));
        data.setDc(ParsingUtils.parseIntegerOrDefault(rowValues[6], 0));
        return data;
    }
}
