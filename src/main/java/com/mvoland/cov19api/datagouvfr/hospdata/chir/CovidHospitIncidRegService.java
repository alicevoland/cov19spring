package com.mvoland.cov19api.datagouvfr.hospdata.chir;

import com.mvoland.cov19api.datagouvfr.common.SourceInfo;
import com.mvoland.cov19api.datagouvfr.common.SourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;

@Service
public class CovidHospitIncidRegService extends SourceService<CovidHospitIncidReg> {

    @Autowired
    public CovidHospitIncidRegService(
            CovidHospitIncidRegParser parser,
            CovidHospitIncidRegProcessor processor
    ) {
        super(new SourceInfo<>(
                "CovidHospitIncidReg",
                "https://www.data.gouv.fr/fr/datasets/r/a1466f7f-4ece-4158-a373-f5d4db167eb0",
                Duration.ofHours(12),
                parser,
                processor
        ));
    }
}
