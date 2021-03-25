package com.mvoland.cov19api.datagouvfr.hospdata.dhcac;

import com.mvoland.cov19api.datagouvfr.common.DataSourceConfig;
import com.mvoland.cov19api.datagouvfr.common.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonneesHospitalieresClasseAgeCovid19Service extends SourceService<DonneesHospitalieresClasseAgeCovid19> {

    @Autowired
    public DonneesHospitalieresClasseAgeCovid19Service(
            DonneesHospitalieresClasseAgeCovid19Parser parser,
            DonneesHospitalieresClasseAgeCovid19Processor processor,
            DonneesHospitalieresClasseAgeCovid19Selector selector
    ) {
        super(
                DataSourceConfig.forHospData(
                        "DonneesHospitalieresClasseAgeCovid19",
                        "https://www.data.gouv.fr/fr/datasets/r/08c18e08-6780-452d-9b8c-ae244ad529b3"
                ),
                parser,
                processor,
                selector
        );
    }
}
