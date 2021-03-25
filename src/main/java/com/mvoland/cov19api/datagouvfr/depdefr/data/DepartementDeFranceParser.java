package com.mvoland.cov19api.datagouvfr.depdefr.data;

import com.mvoland.cov19api.datagouvfr.common.ValueParser;
import org.springframework.stereotype.Component;

@Component
public class DepartementDeFranceParser implements ValueParser<DepartementDeFrance> {
    @Override
    public DepartementDeFrance parse(String[] rowValues) {
        DepartementDeFrance data = new DepartementDeFrance();
        data.setCodeDepartement(rowValues[0]);
        data.setNomDepartement(rowValues[1]);
        data.setCodeRegion(rowValues[2]);
        data.setNomRegion(rowValues[3]);
        return data;
    }
}
