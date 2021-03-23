package com.mvoland.cov19api.datagouvfr.hospdata.source.service;

import com.mvoland.cov19api.datagouvfr.common.AbstractSourceService;
import com.mvoland.cov19api.datagouvfr.hospdata.data.DonneesHospitalieresClasseAgeCovid19;
import com.mvoland.cov19api.datagouvfr.hospdata.source.parse.DonneesHospitalieresClasseAgeCovid19Parser;
import org.springframework.stereotype.Service;

@Service
public class DonneesHospitalieresClasseAgeCovid19SourceService extends AbstractSourceService<DonneesHospitalieresClasseAgeCovid19> {
    public DonneesHospitalieresClasseAgeCovid19SourceService() {
        super(
                "DonneesHospitalieresClasseAgeCovid19",
                "https://www.data.gouv.fr/fr/datasets/r/08c18e08-6780-452d-9b8c-ae244ad529b3",
                new DonneesHospitalieresClasseAgeCovid19Parser());
    }
}
