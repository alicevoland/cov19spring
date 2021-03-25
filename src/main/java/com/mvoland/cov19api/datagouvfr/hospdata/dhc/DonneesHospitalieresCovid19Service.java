package com.mvoland.cov19api.datagouvfr.hospdata.dhc;

import com.mvoland.cov19api.datagouvfr.common.DataSourceConfig;
import com.mvoland.cov19api.datagouvfr.common.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonneesHospitalieresCovid19Service extends SourceService<DonneesHospitalieresCovid19> {

    @Autowired
    public DonneesHospitalieresCovid19Service(
            DonneesHospitalieresCovid19Parser parser,
            DonneesHospitalieresCovid19Processor processor,
            DonneesHospitalieresCovid19Selector selector
    ) {
        super(
                DataSourceConfig.forHospData(
                        "DonneesHospitalieresCovid19",
                        "https://www.data.gouv.fr/fr/datasets/r/63352e38-d353-4b54-bfd1-f1b3ee1cabd7"
                ),
                parser,
                processor,
                selector
        );
    }
}
