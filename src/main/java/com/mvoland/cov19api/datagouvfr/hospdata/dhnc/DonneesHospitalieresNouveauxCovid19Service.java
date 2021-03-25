package com.mvoland.cov19api.datagouvfr.hospdata.dhnc;

import com.mvoland.cov19api.datagouvfr.common.SourceInfo;
import com.mvoland.cov19api.datagouvfr.common.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class DonneesHospitalieresNouveauxCovid19Service extends SourceService<DonneesHospitalieresNouveauxCovid19> {

    @Autowired
    public DonneesHospitalieresNouveauxCovid19Service(
            DonneesHospitalieresNouveauxCovid19Parser parser,
            DonneesHospitalieresNouveauxCovid19Processor processor
    ) {
        super(new SourceInfo<>(
                "DonneesHospitalieresNouveauxCovid19",
                "https://www.data.gouv.fr/fr/datasets/r/6fadff46-9efd-4c53-942a-54aca783c30c",
                Duration.ofHours(12),
                parser,
                processor
        ));
    }
}
