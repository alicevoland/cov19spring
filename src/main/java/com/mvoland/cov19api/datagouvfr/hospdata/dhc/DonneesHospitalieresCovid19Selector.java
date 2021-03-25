package com.mvoland.cov19api.datagouvfr.hospdata.dhc;

import com.mvoland.cov19api.datagouvfr.common.ParsingUtils;
import com.mvoland.cov19api.datagouvfr.common.ValueParser;
import com.mvoland.cov19api.datagouvfr.common.ValueSelector;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DonneesHospitalieresCovid19Selector implements ValueSelector<DonneesHospitalieresCovid19> {
    @Override
    public boolean selectByEntryDateAfter(DonneesHospitalieresCovid19 value, LocalDate date) {
        return value.getJour().isAfter(date);
    }
}
