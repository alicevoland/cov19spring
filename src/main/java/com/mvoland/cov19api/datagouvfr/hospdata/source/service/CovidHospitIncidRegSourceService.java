package com.mvoland.cov19api.datagouvfr.hospdata.source.service;

import com.mvoland.cov19api.datagouvfr.common.AbstractSourceService;
import com.mvoland.cov19api.datagouvfr.hospdata.data.CovidHospitIncidReg;
import com.mvoland.cov19api.datagouvfr.hospdata.source.parse.CovidHospitIncidRegParser;
import org.springframework.stereotype.Service;

@Service
public class CovidHospitIncidRegSourceService extends AbstractSourceService<CovidHospitIncidReg> {
    public CovidHospitIncidRegSourceService() {
        super(
                "CovidHospitIncidReg",
                "https://www.data.gouv.fr/fr/datasets/r/a1466f7f-4ece-4158-a373-f5d4db167eb0",
                new CovidHospitIncidRegParser()
        );
    }
}
