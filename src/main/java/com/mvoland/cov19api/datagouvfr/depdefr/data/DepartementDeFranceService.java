package com.mvoland.cov19api.datagouvfr.depdefr.data;

import com.mvoland.cov19api.datagouvfr.common.DataSourceConfig;
import com.mvoland.cov19api.datagouvfr.common.SourceService;
import com.mvoland.cov19api.datagouvfr.hospdata.chir.CovidHospitIncidReg;
import com.mvoland.cov19api.datagouvfr.hospdata.chir.CovidHospitIncidRegParser;
import com.mvoland.cov19api.datagouvfr.hospdata.chir.CovidHospitIncidRegProcessor;
import com.mvoland.cov19api.datagouvfr.hospdata.chir.CovidHospitIncidRegSelector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
public class DepartementDeFranceService extends SourceService<DepartementDeFrance> {

    @Autowired
    public DepartementDeFranceService(
            DepartementDeFranceParser parser,
            DepartementDeFranceProcessor processor,
            DepartementDeFranceSelector selector
    ) {
        super(
                new DataSourceConfig(
                        "DepartementDeFrance",
                        "https://www.data.gouv.fr/fr/datasets/r/70cef74f-70b1-495a-8500-c089229c0254",
                        ',', '\"', StandardCharsets.UTF_8, 1
                ),
                parser,
                processor,
                selector
        );
    }


}
