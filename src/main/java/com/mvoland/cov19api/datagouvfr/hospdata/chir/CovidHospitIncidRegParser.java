package com.mvoland.cov19api.datagouvfr.hospdata.chir;

import com.mvoland.cov19api.datagouvfr.common.ParsingUtils;
import com.mvoland.cov19api.datagouvfr.common.ValueParser;
import org.springframework.stereotype.Component;

@Component
public class CovidHospitIncidRegParser implements ValueParser<CovidHospitIncidReg> {
    @Override
    public CovidHospitIncidReg parse(String[] rowValues) {
        CovidHospitIncidReg data = new CovidHospitIncidReg();
        data.setJour(ParsingUtils.parseDateOrThrow(rowValues[0]));
        data.setNomReg(rowValues[1]);
        data.setNumReg(ParsingUtils.parseIntegerOrDefault(rowValues[2], null));
        data.setIncid_rea(ParsingUtils.parseIntegerOrDefault(rowValues[3], 0));
        return data;
    }
}
