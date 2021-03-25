package com.mvoland.cov19api.datagouvfr.hospdata.dhcac;

import com.mvoland.cov19api.datagouvfr.common.ParsingUtils;
import com.mvoland.cov19api.datagouvfr.common.ValueParser;
import com.mvoland.cov19api.datagouvfr.common.ValueSelector;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DonneesHospitalieresClasseAgeCovid19Selector implements ValueSelector<DonneesHospitalieresClasseAgeCovid19> {
    @Override
    public boolean selectByEntryDateAfter(DonneesHospitalieresClasseAgeCovid19 value, LocalDate date) {
        return value.getJour().isAfter(date);
    }
}
