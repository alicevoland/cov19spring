package com.mvoland.cov19api.datagouvfr.hospdata.dhnc;

import com.mvoland.cov19api.datagouvfr.common.ValueParser;
import com.mvoland.cov19api.datagouvfr.common.ValueSelector;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DonneesHospitalieresNouveauxCovid19Selector implements ValueSelector<DonneesHospitalieresNouveauxCovid19> {
    @Override
    public boolean selectByEntryDateAfter(DonneesHospitalieresNouveauxCovid19 value, LocalDate date) {
        return value.getJour().isAfter(date);
    }
}
