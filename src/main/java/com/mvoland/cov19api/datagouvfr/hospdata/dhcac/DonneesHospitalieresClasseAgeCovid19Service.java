package com.mvoland.cov19api.datagouvfr.hospdata.dhcac;

import com.mvoland.cov19api.datagouvfr.common.SourceInfo;
import com.mvoland.cov19api.datagouvfr.common.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class DonneesHospitalieresClasseAgeCovid19Service extends SourceService<DonneesHospitalieresClasseAgeCovid19> {

    @Autowired
    public DonneesHospitalieresClasseAgeCovid19Service(
            DonneesHospitalieresClasseAgeCovid19Parser parser,
            DonneesHospitalieresClasseAgeCovid19Processor processor
    ) {
        super(new SourceInfo<>(
                "DonneesHospitalieresClasseAgeCovid19",
                "https://www.data.gouv.fr/fr/datasets/r/08c18e08-6780-452d-9b8c-ae244ad529b3",
                Duration.ofHours(12),
                parser,
                processor
        ));
    }
}
