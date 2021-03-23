package com.mvoland.cov19api.datagouvfr.hospdata.source.service;

import com.mvoland.cov19api.datagouvfr.common.AbstractSourceService;
import com.mvoland.cov19api.datagouvfr.hospdata.data.DonneesHospitalieresCovid19;
import com.mvoland.cov19api.datagouvfr.hospdata.source.parse.DonneesHospitaliersCovid19Parser;
import org.springframework.stereotype.Service;

@Service
public class DonneesHospitalieresCovid19SourceService extends AbstractSourceService<DonneesHospitalieresCovid19> {
    public DonneesHospitalieresCovid19SourceService() {
        super(
                "DonneesHospitalieresCovid19",
                "https://www.data.gouv.fr/fr/datasets/r/63352e38-d353-4b54-bfd1-f1b3ee1cabd7",
                new DonneesHospitaliersCovid19Parser());
    }
}
