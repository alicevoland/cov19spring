package com.mvoland.cov19api.datagouvfr.hospdata.chir;

import com.mvoland.cov19api.datagouvfr.common.ValueSelector;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CovidHospitIncidRegSelector implements ValueSelector<CovidHospitIncidReg> {
    @Override
    public boolean selectByEntryDateAfter(CovidHospitIncidReg value, LocalDate date) {
        return value.getJour().isAfter(date);
    }
}
