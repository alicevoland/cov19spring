package com.mvoland.cov19api.datagouvfr.hospdata.dhnc;

import com.mvoland.cov19api.datagouvfr.common.DataSourceConfig;
import com.mvoland.cov19api.datagouvfr.common.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonneesHospitalieresNouveauxCovid19Service extends SourceService<DonneesHospitalieresNouveauxCovid19> {

    @Autowired
    public DonneesHospitalieresNouveauxCovid19Service(
            DonneesHospitalieresNouveauxCovid19Parser parser,
            DonneesHospitalieresNouveauxCovid19Processor processor,
            DonneesHospitalieresNouveauxCovid19Selector selector
    ) {
        super(
                DataSourceConfig.forHospData(
                        "DonneesHospitalieresNouveauxCovid19",
                        "https://www.data.gouv.fr/fr/datasets/r/6fadff46-9efd-4c53-942a-54aca783c30c"
                ),
                parser,
                processor,
                selector
        );
    }
}
