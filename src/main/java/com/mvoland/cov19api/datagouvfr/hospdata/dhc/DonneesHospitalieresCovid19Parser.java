package com.mvoland.cov19api.datagouvfr.hospdata.dhc;

import com.mvoland.cov19api.datagouvfr.common.ParsingUtils;
import com.mvoland.cov19api.datagouvfr.common.ValueParser;
import org.springframework.stereotype.Component;

@Component
public class DonneesHospitalieresCovid19Parser implements ValueParser<DonneesHospitalieresCovid19> {
    @Override
    public DonneesHospitalieresCovid19 parse(String[] rowValues) {
        DonneesHospitalieresCovid19 data = new DonneesHospitalieresCovid19();
        data.setDep(rowValues[0]);
        data.setSexe(ParsingUtils.parseSexOrThrow(rowValues[1]));
        data.setJour(ParsingUtils.parseDateOrThrow(rowValues[2]));
        data.setHosp(ParsingUtils.parseIntegerOrDefault((rowValues[3]), 0));
        data.setRea(ParsingUtils.parseIntegerOrDefault((rowValues[4]), 0));
        data.setRad(ParsingUtils.parseIntegerOrDefault((rowValues[5]), 0));
        data.setDc(ParsingUtils.parseIntegerOrDefault((rowValues[6]), 0));
        return data;
    }
}
