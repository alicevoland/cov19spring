package com.mvoland.cov19api.datagouvfr.hospdata.source.service;

import com.mvoland.cov19api.datagouvfr.common.AbstractSourceService;
import com.mvoland.cov19api.datagouvfr.hospdata.data.DonneesHospitalieresNouveauxCovid19;
import com.mvoland.cov19api.datagouvfr.hospdata.source.parse.DonneesHospitalieresNouveauxCovid19Parser;
import org.springframework.stereotype.Service;

@Service
public class DonneesHospitalieresNouveauxCovid19SourceService extends AbstractSourceService<DonneesHospitalieresNouveauxCovid19> {
    public DonneesHospitalieresNouveauxCovid19SourceService() {
        super(
                "DonneesHospitalieresNouveauxCovid19",
                "https://www.data.gouv.fr/fr/datasets/r/6fadff46-9efd-4c53-942a-54aca783c30c",
                new DonneesHospitalieresNouveauxCovid19Parser());
    }
}
